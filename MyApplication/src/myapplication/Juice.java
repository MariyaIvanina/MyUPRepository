/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myapplication;

import java.util.ArrayList;

/**
 *
 * @author fpm.pavlovec
 */
public class Juice {
    private ArrayList<String> components;
    
    public Juice(String line)
    {
        String[] parsedData = line.split(" ");
        components = new ArrayList<>();
        for(int i = 0; i < parsedData.length; i++ )
        {
            components.add(parsedData[i]);
        }
    }
    public String toString()
    {
        StringBuilder temp = new StringBuilder();
        for(String component : components)
        {
            temp.append(component);
            temp.append(" ");
        }
        return temp.toString();
    }
    public ArrayList<String> getList()
    {
        return components;
    }
    
}
