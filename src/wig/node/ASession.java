/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class ASession extends PSession
{
    private TSession _session_;
    private TIdentifier _identifier_;
    private TLPar _lPar_;
    private TRPar _rPar_;
    private PCompoundstm _compoundstm_;

    public ASession()
    {
    }

    public ASession(
        TSession _session_,
        TIdentifier _identifier_,
        TLPar _lPar_,
        TRPar _rPar_,
        PCompoundstm _compoundstm_)
    {
        setSession(_session_);

        setIdentifier(_identifier_);

        setLPar(_lPar_);

        setRPar(_rPar_);

        setCompoundstm(_compoundstm_);

    }
    public Object clone()
    {
        return new ASession(
            (TSession) cloneNode(_session_),
            (TIdentifier) cloneNode(_identifier_),
            (TLPar) cloneNode(_lPar_),
            (TRPar) cloneNode(_rPar_),
            (PCompoundstm) cloneNode(_compoundstm_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASession(this);
    }

    public TSession getSession()
    {
        return _session_;
    }

    public void setSession(TSession node)
    {
        if(_session_ != null)
        {
            _session_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _session_ = node;
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

    public TLPar getLPar()
    {
        return _lPar_;
    }

    public void setLPar(TLPar node)
    {
        if(_lPar_ != null)
        {
            _lPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _lPar_ = node;
    }

    public TRPar getRPar()
    {
        return _rPar_;
    }

    public void setRPar(TRPar node)
    {
        if(_rPar_ != null)
        {
            _rPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _rPar_ = node;
    }

    public PCompoundstm getCompoundstm()
    {
        return _compoundstm_;
    }

    public void setCompoundstm(PCompoundstm node)
    {
        if(_compoundstm_ != null)
        {
            _compoundstm_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _compoundstm_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_session_)
            + toString(_identifier_)
            + toString(_lPar_)
            + toString(_rPar_)
            + toString(_compoundstm_);
    }

    void removeChild(Node child)
    {
        if(_session_ == child)
        {
            _session_ = null;
            return;
        }

        if(_identifier_ == child)
        {
            _identifier_ = null;
            return;
        }

        if(_lPar_ == child)
        {
            _lPar_ = null;
            return;
        }

        if(_rPar_ == child)
        {
            _rPar_ = null;
            return;
        }

        if(_compoundstm_ == child)
        {
            _compoundstm_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_session_ == oldChild)
        {
            setSession((TSession) newChild);
            return;
        }

        if(_identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(_lPar_ == oldChild)
        {
            setLPar((TLPar) newChild);
            return;
        }

        if(_rPar_ == oldChild)
        {
            setRPar((TRPar) newChild);
            return;
        }

        if(_compoundstm_ == oldChild)
        {
            setCompoundstm((PCompoundstm) newChild);
            return;
        }

    }
}
