/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gaf.reminderserver.models;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author tnwosu
 */
public class BaseData implements Serializable {
	@Override
    public String toString()
    {
        String value = "";

        Field[] fields = this.getClass().getSuperclass().getDeclaredFields();
        for(Field x : fields)
        {
            try
            {
                x.setAccessible(true);
                value += (x.getName() + " : " + x.get(this) + "\n");
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }

        fields = this.getClass().getDeclaredFields();
        for(Field x : fields)
        {
            try
            {
                x.setAccessible(true);
                value += (x.getName() + " : " + x.get(this) + "\n");
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }

        return value;
    }
}
