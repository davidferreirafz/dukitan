/**
 * 
 */
package com.dukitan.componentes.ui.radio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * @author David Ferreira <davidferreira.fz@gmail.com>
 *
 * Project: F2IBuilder
 * Date: 11/04/2008
 *
 */
public class RadioGroup extends JMenu
{
    private static final long serialVersionUID = -3405989630214570088L;

    private HashMap<String,JRadioButtonMenuItem> table;

    ButtonGroup imageTypeGroup;    
    
    public RadioGroup()
    {
        imageTypeGroup = new ButtonGroup();
        
        table = new HashMap<String,JRadioButtonMenuItem>();
    }    
    
    /* (non-Javadoc)
     * @see javax.swing.JMenu#add(javax.swing.JMenuItem)
     */
    @Override
    public JMenuItem add(JMenuItem item)
    {
        table.put(item.getName(), (JRadioButtonMenuItem) item);
        imageTypeGroup.add(item);
        
        return super.add(item);
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JMenu#remove(javax.swing.JMenuItem)
     */
    @Override
    public void remove(JMenuItem item)
    {
        table.remove(item.getName());
        super.remove(item);
    }
    
    public void setSelected(String key)
    {
        if (table.containsKey(key)){
            table.get(key).setSelected(true);            
        }
    }
    
    public String getSelected()
    {
        String key = "";
        
        Iterator<Entry<String, JRadioButtonMenuItem>> it = table.entrySet().iterator();

        while (it.hasNext()) {
            
            Entry<String, JRadioButtonMenuItem> a = it.next();
            
            if (a.getValue().isSelected()){
                key = a.getKey();
            }
        }    
        
        return key;
    }

        
}
