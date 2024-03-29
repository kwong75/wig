/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class ADefaultJoinExp extends PJoinExp
{
    private PTupleExp _tupleExp_;

    public ADefaultJoinExp()
    {
    }

    public ADefaultJoinExp(
        PTupleExp _tupleExp_)
    {
        setTupleExp(_tupleExp_);

    }
    public Object clone()
    {
        return new ADefaultJoinExp(
            (PTupleExp) cloneNode(_tupleExp_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADefaultJoinExp(this);
    }

    public PTupleExp getTupleExp()
    {
        return _tupleExp_;
    }

    public void setTupleExp(PTupleExp node)
    {
        if(_tupleExp_ != null)
        {
            _tupleExp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _tupleExp_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_tupleExp_);
    }

    void removeChild(Node child)
    {
        if(_tupleExp_ == child)
        {
            _tupleExp_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_tupleExp_ == oldChild)
        {
            setTupleExp((PTupleExp) newChild);
            return;
        }

    }
}
