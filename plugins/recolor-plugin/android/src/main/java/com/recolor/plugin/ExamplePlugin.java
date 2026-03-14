package com.recolor.plugin;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AIRecolor")
public class ExamplePlugin extends Plugin {

    private Example implementation = new Example();

    /**
     * Echo method for testing
     */
    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");
        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    /**
     * Recolors an image given a path and color
     */
    @PluginMethod
    public void recolorImage(PluginCall call) {
        String imagePath = call.getString("path");
        String colorHex = call.getString("color");

        // TODO: implement ML Kit recolor logic
        JSObject ret = new JSObject();
        ret.put("recoloredImagePath", imagePath); // temporary placeholder
        call.resolve(ret);
    }
}