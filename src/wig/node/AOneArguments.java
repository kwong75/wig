/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class AOneArguments extends PArguments
{
    private PArgument _argument_;

    public AOneArguments()
    {
    }

    public AOneArguments(
        PArgument _argument_)
    {
        setArgument(_argument_);

    }
    public Object clone()
    {
        return new AOneArguments(
            (PArgument) cloneNode(_argument_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOneArguments(this);
    }

    public PArgument getArgument()
    {
        return _argument_;
    }

    public void setArgument(PArgument node)
    {
        if(_argument_ != null)
        {
            _argument_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _argument_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_argument_);
    }

    void removeChild(Node child)
    {
        if(_argument_ == child)
        {
            _argument_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_argument_ == oldChild)
        {
            setArgument((PArgument) newChild);
            return;
        }

    }
}
