package com.halil.bookwebsite.seeds;

import org.springframework.context.ApplicationEvent;

public class PermissionsSeededEvent extends ApplicationEvent {

    public PermissionsSeededEvent(Object source) {
        super(source);
    }
}
