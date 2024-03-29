/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class AStringBaseExp extends PBaseExp
{
    private TStringconst _stringconst_;

    public AStringBaseExp()
    {
    }

    public AStringBaseExp(
        TStringconst _stringconst_)
    {
        setStringconst(_stringconst_);

    }
    public Object clone()
    {
        return new AStringBaseExp(
            (TStringconst) cloneNode(_stringconst_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStringBaseExp(this);
    }

    public TStringconst getStringconst()
    {
        return _stringconst_;
    }

    public void setStringconst(TStringconst node)
    {
        if(_stringconst_ != null)
        {
            _stringconst_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _stringconst_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_stringconst_);
    }

    void removeChild(Node child)
    {
        if(_stringconst_ == child)
        {
            _stringconst_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_stringconst_ == oldChild)
        {
            setStringconst((TStringconst) newChild);
            return;
        }

    }
}
