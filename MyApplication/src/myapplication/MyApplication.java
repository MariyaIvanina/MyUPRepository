/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 *
 * @author fpm.pavlovec
 */
public class MyApplication {
    ArrayList<String> mine;
    LinkedList<Juice> tmp;
    int minWashTime = Integer.MAX_VALUE;
    List<Juice> myJuices;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        // TODO code application logic here
        new MyApplication().solveTask();
    }
    public void find(int[] order, int step)
    {
        for(int i = 0; i< myJuices.size(); i++)
        {
            if(order[i] == 0)
            {
                int[] newOrder = Arrays.copyOf(order, order.length);
                newOrder[i] = step;
                find(newOrder,step +1);
            }
        }
        if(step == myJuices.size())
        {
            List<Juice> temp = new ArrayList<>();
            for(int a: order)
                temp.add(myJuices.get(a));
            int count = countMinWashTimes(temp);
            if(minWashTime > count)
                minWashTime = count;
        }
        
    }
    public void solveTask()throws FileNotFoundException, InterruptedException
    {
        myJuices = readFile("juice.in");
        writeDifferentComponents("juice1.out",myJuices);
        writeSortedComponents("juice2.out", myJuices);
        writeMinTimes("juice3.out", myJuices);
        
    }
    
    public int countMinWashTimes(List<Juice> juices) 
    {
        int countWash = 0;
        Stack<Juice> mine = new Stack<Juice>();
        boolean first = true;
        for(Juice juice: juices)
        {
            if(mine.empty())
            {
                mine.push(juice);
            }
            List<String> temp = juice.getList();
            List<String> temp1 = mine.pop().getList();
            boolean flag = false;
            for(String component : temp)
            {
                if(!temp1.contains(component))
                {
                    flag = true;
                    break;
                }
            }
            if(flag)
            {
                mine.clear();
                countWash++;
            }
            mine.push(juice);
            
        }
        return countWash + 1;
    }
    
    public void writeMinTimes(String fileName, List<Juice> juices)
    {
        File file = new File(fileName);
    try {
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        find(new int[myJuices.size()], 0);
        try {
            out.println(minWashTime);
        } finally {
            out.close();
        }
    } catch(IOException e) {
        throw new RuntimeException(e);
    }
    }
    public void writeSortedComponents(String fileName, List<Juice> juices) throws InterruptedException
    {
        mine = new ArrayList<String>();
        for(Juice juice: juices)
        {
            List<String> temp = juice.getList();
            for(String component : temp)
                mine.add(component);
        }
        File file = new File(fileName);
        
        Thread my = new Thread(){
            @Override
            public void run()
            {
                Collections.sort(mine, new Comparator<String>()
                {
                    @Override
                    public int compare(String a, String b)
                    {
                        return a.compareTo(b);
                    }
                });
            }
        };
        my.start();
        while(my.isAlive())
        {
            try{
                this.wait(2000);
            }
            catch(Exception e){}
        }
        
    try {
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
 
        try {
            for(String component : mine)
                out.println(component);
        } finally {
            out.close();
        }
    } catch(IOException e) {
        throw new RuntimeException(e);
    }
    }
    public static void writeDifferentComponents(String fileName, List<Juice> juices)
    {
        HashSet<String> mine = new HashSet<String>();
        for(Juice juice: juices)
        {
            List<String> temp = juice.getList();
            for(String component : temp)
                mine.add(component);
        }
        File file = new File(fileName);
 
    try {
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
 
        try {
            for(String component : mine)
                out.println(component);
        } finally {
            out.close();
        }
    } catch(IOException e) {
        throw new RuntimeException(e);
    }
    }
    public static List<Juice> readFile(String filename)throws FileNotFoundException
    {
        List<Juice> juices = new ArrayList<>();
        try {
        
        BufferedReader in = new BufferedReader(new FileReader( new File(filename)));
        try {
            
            String s;
            while((s = in.readLine())!= null && !"".equals(s.trim()))
            {
                juices.add(new Juice(s));
            }
              
            
        } finally {
            
            in.close();
        }
    } catch(IOException e) {
        throw new RuntimeException(e);
    }
        return juices;
    }
    public static void write(String fileName, List<Juice> juices) {
    //Определяем файл
    File file = new File(fileName);
 
    try {
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        try {
            for(Juice juice : juices)
                out.println(juice);
        } finally {
            out.close();
        }
    } catch(IOException e) {
        throw new RuntimeException(e);
    }
}
}
