package com.xioa.conf;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xioa on 2016/6/11.
 */
public class UserMultipartResolver extends CommonsMultipartResolver {

    @Autowired
    private FileUploadProgressListener fileUploadProgressListener;

    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = this.determineEncoding(request);
        FileUpload fileUpload = this.prepareFileUpload(encoding);

        fileUploadProgressListener.setSession(request.getSession());
        fileUpload.setProgressListener(fileUploadProgressListener);

        try {
            List ex = ((ServletFileUpload)fileUpload).parseRequest(request);
            return this.parseFileItems(ex, encoding);
        } catch (FileUploadBase.SizeLimitExceededException var5) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), var5);
        } catch (FileUploadException var6) {
            throw new MultipartException("Could not parse multipart servlet request", var6);
        }
    }
}
