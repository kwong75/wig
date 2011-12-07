/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class ANameInputattr extends PInputattr
{
    private TName _name_;
    private TAssign _assign_;
    private PAttr _attr_;

    public ANameInputattr()
    {
    }

    public ANameInputattr(
        TName _name_,
        TAssign _assign_,
        PAttr _attr_)
    {
        setName(_name_);

        setAssign(_assign_);

        setAttr(_attr_);

    }
    public Object clone()
    {
        return new ANameInputattr(
            (TName) cloneNode(_name_),
            (TAssign) cloneNode(_assign_),
            (PAttr) cloneNode(_attr_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANameInputattr(this);
    }

    public TName getName()
    {
        return _name_;
    }

    public void setName(TName node)
    {
        if(_name_ != null)
        {
            _name_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _name_ = node;
    }

    public TAssign getAssign()
    {
        return _assign_;
    }

    public void setAssign(TAssign node)
    {
        if(_assign_ != null)
        {
            _assign_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _assign_ = node;
    }

    public PAttr getAttr()
    {
        return _attr_;
    }

    public void setAttr(PAttr node)
    {
        if(_attr_ != null)
        {
            _attr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _attr_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_name_)
            + toString(_assign_)
            + toString(_attr_);
    }

    void removeChild(Node child)
    {
        if(_name_ == child)
        {
            _name_ = null;
            return;
        }

        if(_assign_ == child)
        {
            _assign_ = null;
            return;
        }

        if(_attr_ == child)
        {
            _attr_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_name_ == oldChild)
        {
            setName((TName) newChild);
            return;
        }

        if(_assign_ == oldChild)
        {
            setAssign((TAssign) newChild);
            return;
        }

        if(_attr_ == oldChild)
        {
            setAttr((PAttr) newChild);
            return;
        }

    }
}
