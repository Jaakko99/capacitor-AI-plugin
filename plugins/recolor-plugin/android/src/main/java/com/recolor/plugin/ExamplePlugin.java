package com.recolor.plugin;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AIRecolor")
public class ExamplePlugin extends Plugin {

    private Example implementation = new Example();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    fun recolorImage(call: PluginCall)
        val imagePath = call.getString("path")
        val colorHex = call-getString("color")
                // Logic to proces image with ml kit
    // Retunr result
    val ret = JSObject()
    ret.put("status","success")
    call.resolve
}
