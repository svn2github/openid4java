/*
 * Copyright 2006 Sxip Identity Corporation
 */

package net.openid.message;
   
import java.util.List;
import java.util.Arrays;

/**
 * @author Marius Scurtescu, Johnny Bufu
 */
public class AuthFailure extends Message
{

    protected final static List requiredFields = Arrays.asList( new String[] {
            "openid.mode"
    });

    protected final static List optionalFields = Arrays.asList( new String[] {
            "openid.ns"
    });

    public AuthFailure(boolean compatibility)
    {
        set("openid.mode", MODE_CANCEL);

        if (! compatibility)
            set("openid.ns", OPENID2_NS);
    }

    protected AuthFailure(ParameterList params)
    {
        super(params);
    }

    public static AuthFailure createAuthFailure(ParameterList params) throws
            MessageException
    {
        AuthFailure fail = new AuthFailure(params);

        if (! fail.isValid()) throw new MessageException(
                "Invalid set of parameters for the requested message type");

        return fail;
    }

    public List getRequiredFields()
    {
        return requiredFields;
    }

    public boolean isVersion2()
    {
        return hasParameter("openid.ns") &&
                OPENID2_NS.equals(getParameterValue("openid.ns"));
    }

    public boolean isValid()
    {
        if (!super.isValid()) return false;

        return MODE_CANCEL.equals(getParameterValue("openid.mode"));
    }
}
