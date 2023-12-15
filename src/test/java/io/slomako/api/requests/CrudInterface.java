package io.slomako.api.requests;

import io.slomako.api.models.NewProjectDescription;

public interface CrudInterface {

    public Object create(Object obj);

    public Object get(String id);

    public Object update(String id, Object obj);

    public Object delete(String id);
}
