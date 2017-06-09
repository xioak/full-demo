package task;

import com.alibaba.fastjson.JSON;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.PropertySet;
import org.mybatis.generator.ant.AntProgressCallback;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * Created by xioa on 2016/9/2.
 */
public class MyGenerTask extends Task {

    private String configfile;
    private boolean overwrite;
    private PropertySet propertyset;
    private boolean verbose;
    private String contextIds;
    private String fullyQualifiedTableNames;

    /**
     *
     */
    public MyGenerTask() {
        super();
        super.log("ant task init start....");
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.tools.ant.Task#execute()
     */
    @Override
    public void execute() throws BuildException {


        super.log("ant task start....");

        if (!stringHasValue(configfile)) {
            throw new BuildException(getString("RuntimeError.0")); //$NON-NLS-1$
        }

        List<String> warnings = new ArrayList<String>();

        File configurationFile = new File(configfile);
        if (!configurationFile.exists()) {
            throw new BuildException(getString(
                    "RuntimeError.1", configfile)); //$NON-NLS-1$
        }

        Set<String> fullyqualifiedTables = new HashSet<String>();
        if (stringHasValue(fullyQualifiedTableNames)) {
            StringTokenizer st = new StringTokenizer(fullyQualifiedTableNames,
                    ","); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String s = st.nextToken().trim();
                if (s.length() > 0) {


                    super.log("fullyQualifiedTableName： " + s);

                    fullyqualifiedTables.add(s);
                }
            }
        }

        Set<String> contexts = new HashSet<String>();
        if (stringHasValue(contextIds)) {
            StringTokenizer st = new StringTokenizer(contextIds, ","); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String s = st.nextToken().trim();
                if (s.length() > 0) {
                    super.log("contextIds： " + s);
                    contexts.add(s);
                }
            }
        }

        try {
            Properties p = propertyset == null ? null : propertyset
                    .getProperties();

            ConfigurationParser cp = new ConfigurationParser(p, warnings);
            Configuration config = cp.parseConfiguration(configurationFile);

            super.log("ConfigurationParser： ");


            DefaultShellCallback callback = new DefaultShellCallback(overwrite);

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

            myBatisGenerator.generate(new AntProgressCallback(this, verbose), contexts,
                    fullyqualifiedTables);

        } catch (XMLParserException e) {
            for (String error : e.getErrors()) {
                log(error, Project.MSG_ERR);
            }

            throw new BuildException(e.getMessage());
        } catch (SQLException e) {
            throw new BuildException(e.getMessage());
        } catch (IOException e) {
            throw new BuildException(e.getMessage());
        } catch (InvalidConfigurationException e) {
            for (String error : e.getErrors()) {
                log(error, Project.MSG_ERR);
            }

            throw new BuildException(e.getMessage());
        } catch (InterruptedException e) {
            // ignore (will never happen with the DefaultShellCallback)
        } catch (Exception e) {
            e.printStackTrace();
            throw new BuildException(e.getMessage());
        }

        for (String error : warnings) {
            log(error, Project.MSG_WARN);
        }
    }

    /**
     * @return Returns the configfile.
     */
    public String getConfigfile() {
        return configfile;
    }

    /**
     * @param configfile The configfile to set.
     */
    public void setConfigfile(String configfile) {
        this.configfile = configfile;
    }

    /**
     * @return Returns the overwrite.
     */
    public boolean isOverwrite() {
        return overwrite;
    }

    /**
     * @param overwrite The overwrite to set.
     */
    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public PropertySet createPropertyset() {
        if (propertyset == null) {
            propertyset = new PropertySet();
        }

        return propertyset;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String getContextIds() {
        return contextIds;
    }

    public void setContextIds(String contextIds) {
        this.contextIds = contextIds;
    }

    public String getFullyQualifiedTableNames() {
        return fullyQualifiedTableNames;
    }

    public void setFullyQualifiedTableNames(String fullyQualifiedTableNames) {
        this.fullyQualifiedTableNames = fullyQualifiedTableNames;
    }
}
