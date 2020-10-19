package com.example.dome.configures.imports;

import com.example.project.entity.Monkey;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/**
 * Created by Tom.
 */
public class ImportsFactoryBean implements FactoryBean<Monkey> {
    @Nullable
    @Override
    public Monkey getObject() throws Exception {
        return new Monkey();
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return Monkey.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
