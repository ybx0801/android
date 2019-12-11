package com.example.helloworld;

public class huansuan {

    public static String d(String s,String s1,String s2){
        String d="";
        double e=0;
        int i=0;
        switch (s1){
            case "cm":
                switch (s2){
                    case "cm":
                        d=s;
                        break;
                    case "dm":
                        e=Double.valueOf(s);
                        e=e/10;
                        d=String.valueOf(e);
                        break;
                    case "m":
                        e=Double.valueOf(s);
                        e=e/100;
                        d=String.valueOf(e);
                        break;
                    case "km":
                        e=Double.valueOf(s);
                        e=e/1000000;
                        d=String.valueOf(e);
                        break;
                }
                break;
            case "dm":
                switch (s2){
                    case"cm":
                        e=Double.valueOf(s);
                        e=e*10;
                        d=String.valueOf(e);
                        break;
                    case"dm":
                        d=s;
                        break;
                    case "m":
                        e=Double.valueOf(s);
                        e=e/10;
                        d=String.valueOf(e);
                        break;
                    case"km":
                        e=Double.valueOf(s);
                        e=e/100000;
                        d=String.valueOf(e);
                        break;
                }
                break;
            case "m":
                switch (s2){
                    case"cm":
                        e=Double.valueOf(s);
                        e=e*100;
                        d=String.valueOf(e);
                        break;
                    case"dm":
                        e=Double.valueOf(s);
                        e=e*10;
                        d=String.valueOf(e);
                        break;
                    case "m":
                       d=s;
                        break;
                    case"km":
                        e=Double.valueOf(s);
                        e=e/1000;
                        d=String.valueOf(e);
                        break;
                }
                break;
            case "km":
                switch (s2){
                    case"cm":
                        e=Double.valueOf(s);
                        e=e*1000000;
                        d=String.valueOf(e);
                        break;
                    case"dm":
                        e=Double.valueOf(s);
                        e=e*10000;
                        d=String.valueOf(e);
                        break;
                    case "m":
                        e=Double.valueOf(s);
                        e=e*1000;
                        d=String.valueOf(e);
                        break;
                    case"km":
                        d=s;
                        break;
                }
                break;
            case"g":
                switch (s2){
                    case"g":
                        d=s;
                        break;
                    case"kg":
                        e=Double.valueOf(s);
                        e=e/1000;
                        d=String.valueOf(e);
                        break;
                    case"t":
                        e=Double.valueOf(s);
                        e=e/1000000;
                        d=String.valueOf(e);
                        break;
                }
                break;
            case"kg":
                switch (s2){
                    case"g":
                        e=Double.valueOf(s);
                        e=e*1000;
                        d=String.valueOf(e);
                        break;
                    case"kg":
                        d=s;
                        break;
                    case"t":
                        e=Double.valueOf(s);
                        e=e/1000;
                        d=String.valueOf(e);
                        break;
                }
                break;
            case "t":
                switch (s2){
                    case"g":
                        e=Double.valueOf(s);
                        e=e*1000000;
                        d=String.valueOf(e);
                        break;
                    case"kg":
                        e=Double.valueOf(s);
                        e=e*1000;
                        d=String.valueOf(s);
                        break;
                    case"t":
                        d=s;
                        break;
                }
                break;
            case"cm^3":
                switch (s2){
                    case"cm^3":
                        d=s;
                        break;
                    case"dm^3":
                        e=Double.valueOf(s);
                        e=e/1000;
                        d=String.valueOf(e);
                        break;
                    case"m^3":
                        e=Double.valueOf(s);
                        e=e/1000000;
                        d=String.valueOf(e);
                        break;
                }
                break;
            case"dm^3":
                switch (s2){
                    case"cm^3":
                        e=Double.valueOf(s);
                        e=e*1000;
                        d=String.valueOf(e);
                        break;
                    case "dm^3":
                        d=s;
                        break;
                    case"m^3":
                        e=Double.valueOf(s);
                        e=e/1000;
                        d=String.valueOf(e);
                        break;
                }
                break;
            case"m^3":
                switch (s2){
                    case"cm^3":
                        e=Double.valueOf(s);
                        e=e*1000000;
                        d=String.valueOf(e);
                        break;
                    case "dm^3":
                        e=Double.valueOf(s);
                        e=e*1000;
                        d=String.valueOf(e);
                        break;
                    case"m^3":
                       d=s;
                       break;
                }
                break;
            case"s":
                switch (s2){
                    case"s":
                        d=s;
                        break;
                    case "min":
                        e=Double.valueOf(s);
                        e=e/60;
                        d=String.valueOf(e);
                        break;
                    case"h":
                        e=Double.valueOf(s);
                        e=e/3600;
                        d=String.valueOf(e);
                        break;
                }
                break;
            case"min":
                switch (s2){
                    case"s":
                        e=Double.valueOf(s);
                        e=e*60;
                        d=String.valueOf(e);
                        break;
                    case"min":
                        d=s;
                        break;
                    case"h":
                        e=Double.valueOf(s);
                        e=e/60;
                        d=String.valueOf(e);
                        break;
                }
                break;
            case"h":
                switch (s2){
                    case"s":
                        e=Double.valueOf(s);
                        e=e*3600;
                        d=String.valueOf(e);
                        break;
                    case"min":
                        e=Double.valueOf(s);
                        e=e*60;
                        d=String.valueOf(e);
                        break;
                    case "h":
                        d=s;
                        break;
                }
                break;
            case"10":
                switch (s2){
                    case "10":
                        d=s;
                        break;
                    case"2":
                        i=Integer.parseInt(s);
                        d=Integer.toString(i,2);
                        break;
                    case"16":
                        i=Integer.parseInt(s);
                        d=Integer.toString(i,16);
                        break;
                }
                break;
            case"2":
                switch (s2){
                    case"10":
                        i=Integer.parseInt(s,2);
                        d=String.valueOf(i);
                        break;
                    case"2":
                        d=s;
                        break;
                    case"16":
                        i=Integer.parseInt(s,2);
                        d=Integer.toString(i,16);
                        break;
                }
                break;
            case"16":
                switch (s2){
                    case"10":
                        i=Integer.parseInt(s,16);
                        d=String.valueOf(i);
                        break;
                    case"2":
                        i=Integer.parseInt(s,16);
                        d=Integer.toString(i,2);
                        break;
                    case"16":
                        d=s;
                        break;
                }
                break;
        }
        return d;
    }
}
