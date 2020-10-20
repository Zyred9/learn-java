package com.example.spring.framework.mvc.servlet;

import java.io.File;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";
    private File templateRootDir;
    private File rootFile;

    public ViewResolver(File templateRoot, File rootFile) {
//        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        templateRootDir = templateRoot;
        this.rootFile = rootFile;
    }

    public View resolveViewName(String viewName) {
        if (Objects.equals("", viewName.trim()) || Objects.isNull(viewName)) {
            return null;
        }
        viewName = viewName.endsWith(this.DEFAULT_TEMPLATE_SUFFIX)
                ? viewName : viewName.concat(this.DEFAULT_TEMPLATE_SUFFIX);

        File templateFile = new File((rootFile.getPath() + "/" + viewName).replaceAll("/+","/"));
        return new View(templateFile);
    }
}
