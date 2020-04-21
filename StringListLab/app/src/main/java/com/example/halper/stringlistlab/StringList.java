package com.example.halper.stringlistlab;

/**
 * Created by halper.
 */

import java.util.LinkedList;

public final class StringList extends LinkedList<String>
{

    private static StringList instance = null;

    private StringList()
    {
        // Exists only to defeat additional instantiations.
    }

    public static StringList getInstance()
    {
        if(instance == null)
            instance = new StringList();

        return instance;
    }

} // end StringList

