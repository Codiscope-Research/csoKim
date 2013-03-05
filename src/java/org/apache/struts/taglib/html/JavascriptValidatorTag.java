// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 16/05/2008 14:02:20
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   JavascriptValidatorTag.java

package org.apache.struts.taglib.html;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.commons.validator.*;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.Resources;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class JavascriptValidatorTag extends BodyTagSupport
{

    public JavascriptValidatorTag()
    {
        bundle = "org.apache.struts.action.MESSAGE";
        formName = null;
        jsFormName = null;
        page = 0;
        methodName = null;
        scriptLanguage = true;
        staticJavascript = "true";
        dynamicJavascript = "true";
        src = null;
        htmlComment = "true";
        cdata = "true";
    }

    public String getFormName()
    {
        return formName;
    }

    public void setFormName(String formName)
    {
        this.formName = formName;
    }

    public String getJsFormName()
    {
        return jsFormName;
    }

    public void setJsFormName(String jsFormName)
    {
        this.jsFormName = jsFormName;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public String getMethod()
    {
        return methodName;
    }

    public void setMethod(String methodName)
    {
        this.methodName = methodName;
    }

    public String getStaticJavascript()
    {
        return staticJavascript;
    }

    public void setStaticJavascript(String staticJavascript)
    {
        this.staticJavascript = staticJavascript;
    }

    public String getDynamicJavascript()
    {
        return dynamicJavascript;
    }

    public void setDynamicJavascript(String dynamicJavascript)
    {
        this.dynamicJavascript = dynamicJavascript;
    }

    public String getHtmlComment()
    {
        return htmlComment;
    }

    public void setHtmlComment(String htmlComment)
    {
        this.htmlComment = htmlComment;
    }

    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }

    public String getBundle()
    {
        return bundle;
    }

    public void setBundle(String bundle)
    {
        this.bundle = bundle;
    }

    public int doStartTag()
        throws JspException
    {
        JspWriter writer = pageContext.getOut();
        try
        {
            writer.print(renderJavascript());
        }
        catch(IOException e)
        {
            throw new JspException(e.getMessage());
        }
        return 2;
    }

    protected String renderJavascript()
        throws JspException
    {
        StringBuffer results = new StringBuffer();
        ModuleConfig config = TagUtils.getInstance().getModuleConfig(pageContext);
        ValidatorResources resources = (ValidatorResources)pageContext.getAttribute("org.apache.commons.validator.VALIDATOR_RESOURCES" + config.getPrefix(), 4);
        if(resources == null)
            throw new JspException("ValidatorResources not found in application scope under key \"org.apache.commons.validator.VALIDATOR_RESOURCES" + config.getPrefix() + "\"");
        Locale locale = TagUtils.getInstance().getUserLocale(pageContext, null);
        Form form = null;
        if("true".equalsIgnoreCase(dynamicJavascript))
        {
            form = resources.getForm(locale, formName);
            if(form == null)
                throw new JspException("No form found under '" + formName + "' in locale '" + locale + "'.  A form must be defined in the " + "Commons Validator configuration when " + "dynamicJavascript=\"true\" is set.");
        }
        if(form != null)
            if("true".equalsIgnoreCase(dynamicJavascript))
                results.append(createDynamicJavascript(config, resources, locale, form));
            else
            if("true".equalsIgnoreCase(staticJavascript))
            {
                results.append(renderStartElement());
                if("true".equalsIgnoreCase(htmlComment))
                    results.append("\n<!-- Begin \n");
            }
        if("true".equalsIgnoreCase(staticJavascript))
            results.append(getJavascriptStaticMethods(resources));
        if(form != null && ("true".equalsIgnoreCase(dynamicJavascript) || "true".equalsIgnoreCase(staticJavascript)))
            results.append(getJavascriptEnd());
        return results.toString();
    }

    private String createDynamicJavascript(ModuleConfig config, ValidatorResources resources, Locale locale, Form form)
        throws JspException
    {
        StringBuffer results = new StringBuffer();
        MessageResources messages = TagUtils.getInstance().retrieveMessageResources(pageContext, bundle, true);
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        javax.servlet.ServletContext application = pageContext.getServletContext();
        List actions = createActionList(resources, form);
        String methods = createMethods(actions, stopOnError(config));
        String formName = form.getName();
        jsFormName = formName;
        if(jsFormName.charAt(0) == '/')
        {
            String mappingName = TagUtils.getInstance().getActionMappingName(jsFormName);
            ActionMapping mapping = (ActionMapping)config.findActionConfig(mappingName);
            if(mapping == null)
            {
                JspException e = new JspException(messages.getMessage("formTag.mapping", mappingName));
                pageContext.setAttribute("org.apache.struts.action.EXCEPTION", e, 2);
                throw e;
            }
            jsFormName = mapping.getAttribute();
        }
        results.append(getJavascriptBegin(methods));
label0:
        for(Iterator i = actions.iterator(); i.hasNext(); results.append("    } \n\n"))
        {
            ValidatorAction va = (ValidatorAction)i.next();
            int jscriptVar = 0;
            String functionName = null;
            if(va.getJsFunctionName() != null && va.getJsFunctionName().length() > 0)
                functionName = va.getJsFunctionName();
            else
                functionName = va.getName();
            results.append("    function " + jsFormName + "_" + functionName + " () { \n");
            Iterator x = form.getFields().iterator();
            do
            {
                if(!x.hasNext())
                    continue label0;
                Field field = (Field)x.next();
                if(!field.isIndexed() && field.getPage() == page && field.isDependency(va.getName()))
                {
                    String message = Resources.getMessage(application, request, messages, locale, va, field);
                    message = message == null ? "" : message;
                    results.append("     this.a" + jscriptVar++ + " = new Array(\"" + field.getKey() + "\", \"" + escapeQuotes(message) + "\", ");
                    results.append("new Function (\"varName\", \"");
                    Map vars = field.getVars();
                    Iterator varsIterator = vars.keySet().iterator();
                    do
                    {
                        if(!varsIterator.hasNext())
                            break;
                        String varName = (String)varsIterator.next();
                        Var var = (Var)vars.get(varName);
                        String varValue = Resources.getVarValue(var, application, request, false);
                        String jsType = var.getJsType();
                        if(!varName.startsWith("field"))
                        {
                            String varValueEscaped = escapeJavascript(varValue);
                            if("int".equalsIgnoreCase(jsType))
                                results.append("this." + varName + "=" + varValueEscaped + "; ");
                            else
                            if("regexp".equalsIgnoreCase(jsType))
                                results.append("this." + varName + "=/" + varValueEscaped + "/; ");
                            else
                            if("string".equalsIgnoreCase(jsType))
                                results.append("this." + varName + "='" + varValueEscaped + "'; ");
                            else
                            if("mask".equalsIgnoreCase(varName))
                                results.append("this." + varName + "=/" + varValueEscaped + "/; ");
                            else
                                results.append("this." + varName + "='" + varValueEscaped + "'; ");
                        }
                    } while(true);
                    results.append(" return this[varName];\"));\n");
                }
            } while(true);
        }

        return results.toString();
    }

    private String escapeQuotes(String in)
    {
        if(in == null || in.indexOf("\"") == -1)
            return in;
        StringBuffer buffer = new StringBuffer();
        String token;
        for(StringTokenizer tokenizer = new StringTokenizer(in, "\"", true); tokenizer.hasMoreTokens(); buffer.append(token))
        {
            token = tokenizer.nextToken();
            if(token.equals("\""))
                buffer.append("\\");
        }

        return buffer.toString();
    }

    private String escapeJavascript(String str)
    {
        if(str == null)
            return null;
        int length = str.length();
        if(length == 0)
            return str;
        StringBuffer out = new StringBuffer(length + 4);
        for(int i = 0; i < length; i++)
        {
            char c = str.charAt(i);
            if(c == '"' || c == '\'' || c == '\\' || c == '\n' || c == '\r')
                out.append('\\');
            out.append(c);
        }

        return out.toString();
    }

    private boolean stopOnError(ModuleConfig config)
    {
        Object stopOnErrorObj = pageContext.getAttribute("org.apache.struts.validator.STOP_ON_ERROR." + config.getPrefix(), 4);
        boolean stopOnError = true;
        if(stopOnErrorObj instanceof Boolean)
            stopOnError = ((Boolean)stopOnErrorObj).booleanValue();
        return stopOnError;
    }

    private String createMethods(List actions, boolean stopOnError)
    {
        StringBuffer methods = new StringBuffer();
        String methodOperator = stopOnError ? " && " : " & ";
        ValidatorAction va;
        for(Iterator iter = actions.iterator(); iter.hasNext(); methods.append(va.getMethod()).append("(form)"))
        {
            va = (ValidatorAction)iter.next();
            if(methods.length() > 0)
                methods.append(methodOperator);
        }

        return methods.toString();
    }

    private List createActionList(ValidatorResources resources, Form form)
    {
        List actionMethods = new ArrayList();
        for(Iterator iterator = form.getFields().iterator(); iterator.hasNext();)
        {
            Field field = (Field)iterator.next();
            Iterator x = field.getDependencyList().iterator();
            while(x.hasNext()) 
            {
                Object o = x.next();
                if(o != null && !actionMethods.contains(o))
                    actionMethods.add(o);
            }
        }

        List actions = new ArrayList();
        for(Iterator iterator = actionMethods.iterator(); iterator.hasNext();)
        {
            String depends = (String)iterator.next();
            ValidatorAction va = resources.getValidatorAction(depends);
            if(va == null)
                throw new NullPointerException("Depends string \"" + depends + "\" was not found in validator-rules.xml.");
            if(va.getJavascript() != null && va.getJavascript().length() > 0)
                actions.add(va);
            else
                iterator.remove();
        }

        Collections.sort(actions, actionComparator);
        return actions;
    }

    public void release()
    {
        super.release();
        bundle = "org.apache.struts.action.MESSAGE";
        formName = null;
        jsFormName = null;
        page = 0;
        methodName = null;
        staticJavascript = "true";
        dynamicJavascript = "true";
        htmlComment = "true";
        cdata = "true";
        src = null;
    }

    protected String getJavascriptBegin(String methods)
    {
        StringBuffer sb = new StringBuffer();
        String name = jsFormName.replace('/', '_');
        name = jsFormName.substring(0, 1).toUpperCase() + jsFormName.substring(1, jsFormName.length());
        sb.append(renderStartElement());
        if(isXhtml() && "true".equalsIgnoreCase(cdata))
            sb.append("//<![CDATA[\r\n");
        if(!isXhtml() && "true".equals(htmlComment))
            sb.append("\n<!-- Begin \n");
        sb.append("\n    var bCancel = false; \n\n");
        if(methodName == null || methodName.length() == 0)
            sb.append("    function validate" + name + "(form) { \n");
        else
            sb.append("    function " + methodName + "(form) { \n");
        sb.append("        if (bCancel) { \n");
        sb.append("            return true; \n");
        sb.append("        } else { \n");
        if(methods == null || methods.length() == 0)
        {
            sb.append("            return true; \n");
        } else
        {
            sb.append("            jcv_initInputs(form); \n");
            sb.append("            var formValidationResult; \n");
            sb.append("            formValidationResult = " + methods + "; \n");
            if(methods.indexOf("&&") >= 0)
                sb.append("            return (formValidationResult); \n");
            else
                sb.append("            return (formValidationResult == 1); \n");
        }
        sb.append("        } \n");
        sb.append("    } \n\n");
        return sb.toString();
    }

    protected String getJavascriptStaticMethods(ValidatorResources resources)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("\n\n");
        Iterator actions = resources.getValidatorActions().values().iterator();
        do
        {
            if(!actions.hasNext())
                break;
            ValidatorAction va = (ValidatorAction)actions.next();
            if(va != null)
            {
                String javascript = va.getJavascript();
                if(javascript != null && javascript.length() > 0)
                    sb.append(javascript + "\n");
            }
        } while(true);
        return sb.toString();
    }

    protected String getJavascriptEnd()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        if(!isXhtml() && "true".equals(htmlComment))
            sb.append("//End --> \n");
        if(isXhtml() && "true".equalsIgnoreCase(cdata))
            sb.append("//]]>\r\n");
        sb.append("</script>\n\n");
        return sb.toString();
    }

    protected String renderStartElement()
    {
        StringBuffer start = new StringBuffer("<script type=\"text/javascript\"");
        if(!isXhtml() && scriptLanguage)
            start.append(" language=\"Javascript1.1\"");
        if(src != null)
            start.append(" src=\"" + src + "\"");
        start.append("> \n");
        return start.toString();
    }

    private boolean isXhtml()
    {
        return TagUtils.getInstance().isXhtml(pageContext);
    }

    public String getCdata()
    {
        return cdata;
    }

    public void setCdata(String cdata)
    {
        this.cdata = cdata;
    }

    public boolean getScriptLanguage()
    {
        return scriptLanguage;
    }

    public void setScriptLanguage(boolean scriptLanguage)
    {
        this.scriptLanguage = scriptLanguage;
    }

    private static final Comparator actionComparator = new Comparator() {

        public int compare(Object o1, Object o2)
        {
            ValidatorAction va1 = (ValidatorAction)o1;
            ValidatorAction va2 = (ValidatorAction)o2;
            if((va1.getDepends() == null || va1.getDepends().length() == 0) && (va2.getDepends() == null || va2.getDepends().length() == 0))
                return 0;
            if(va1.getDepends() != null && va1.getDepends().length() > 0 && (va2.getDepends() == null || va2.getDepends().length() == 0))
                return 1;
            if((va1.getDepends() == null || va1.getDepends().length() == 0) && va2.getDepends() != null && va2.getDepends().length() > 0)
                return -1;
            else
                return va1.getDependencyList().size() - va2.getDependencyList().size();
        }

    };
    protected static final String HTML_BEGIN_COMMENT = "\n<!-- Begin \n";
    protected static final String HTML_END_COMMENT = "//End --> \n";
    protected static String lineEnd = System.getProperty("line.separator");
    protected String bundle;
    protected String formName;
    protected String jsFormName;
    protected int page;
    protected String methodName;
    protected boolean scriptLanguage;
    protected String staticJavascript;
    protected String dynamicJavascript;
    protected String src;
    protected String htmlComment;
    protected String cdata;

}