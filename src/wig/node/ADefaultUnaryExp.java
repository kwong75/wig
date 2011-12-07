/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class ADefaultUnaryExp extends PUnaryExp
{
    private PBaseExp _baseExp_;

    public ADefaultUnaryExp()
    {
    }

    public ADefaultUnaryExp(
        PBaseExp _baseExp_)
    {
        setBaseExp(_baseExp_);

    }
    public Object clone()
    {
        return new ADefaultUnaryExp(
            (PBaseExp) cloneNode(_baseExp_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADefaultUnaryExp(this);
    }

    public PBaseExp getBaseExp()
    {
        return _baseExp_;
    }

    public void setBaseExp(PBaseExp node)
    {
        if(_baseExp_ != null)
        {
            _baseExp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _baseExp_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_baseExp_);
    }

    void removeChild(Node child)
    {
        if(_baseExp_ == child)
        {
            _baseExp_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_baseExp_ == oldChild)
        {
            setBaseExp((PBaseExp) newChild);
            return;
        }

    }
}