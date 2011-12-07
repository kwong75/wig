/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class ARemoveTupleExp extends PTupleExp
{
    private PTupleExp _tupleExp_;
    private TRemove _remove_;
    private TIdentifier _identifier_;

    public ARemoveTupleExp()
    {
    }

    public ARemoveTupleExp(
        PTupleExp _tupleExp_,
        TRemove _remove_,
        TIdentifier _identifier_)
    {
        setTupleExp(_tupleExp_);

        setRemove(_remove_);

        setIdentifier(_identifier_);

    }
    public Object clone()
    {
        return new ARemoveTupleExp(
            (PTupleExp) cloneNode(_tupleExp_),
            (TRemove) cloneNode(_remove_),
            (TIdentifier) cloneNode(_identifier_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseARemoveTupleExp(this);
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

    public TRemove getRemove()
    {
        return _remove_;
    }

    public void setRemove(TRemove node)
    {
        if(_remove_ != null)
        {
            _remove_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _remove_ = node;
    }

    public TIdentifier getIdentifier()
    {
        return _identifier_;
    }

    public void setIdentifier(TIdentifier node)
    {
        if(_identifier_ != null)
        {
            _identifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _identifier_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_tupleExp_)
            + toString(_remove_)
            + toString(_identifier_);
    }

    void removeChild(Node child)
    {
        if(_tupleExp_ == child)
        {
            _tupleExp_ = null;
            return;
        }

        if(_remove_ == child)
        {
            _remove_ = null;
            return;
        }

        if(_identifier_ == child)
        {
            _identifier_ = null;
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

        if(_remove_ == oldChild)
        {
            setRemove((TRemove) newChild);
            return;
        }

        if(_identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

    }
}
