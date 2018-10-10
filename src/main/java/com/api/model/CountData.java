package com.api.model;

/**
 * Created by zy on 2018/5/29.
 */
public class CountData {

    private String ref_date;

    private int session_cnt;

    private int visit_pv;

    private int visit_uv;

    private int visit_uv_new;

    private float stay_time_session;

    private float visit_depth;

    public String getRef_date() {
        return ref_date;
    }

    public void setRef_date(String ref_date) {
        this.ref_date = ref_date;
    }

    public int getSession_cnt() {
        return session_cnt;
    }

    public void setSession_cnt(int session_cnt) {
        this.session_cnt = session_cnt;
    }

    public int getVisit_pv() {
        return visit_pv;
    }

    public void setVisit_pv(int visit_pv) {
        this.visit_pv = visit_pv;
    }

    public int getVisit_uv() {
        return visit_uv;
    }

    public void setVisit_uv(int visit_uv) {
        this.visit_uv = visit_uv;
    }

    public int getVisit_uv_new() {
        return visit_uv_new;
    }

    public void setVisit_uv_new(int visit_uv_new) {
        this.visit_uv_new = visit_uv_new;
    }

    public float getStay_time_session() {
        return stay_time_session;
    }

    public void setStay_time_session(float stay_time_session) {
        this.stay_time_session = stay_time_session;
    }

    public float getVisit_depth() {
        return visit_depth;
    }

    public void setVisit_depth(float visit_depth) {
        this.visit_depth = visit_depth;
    }
}
