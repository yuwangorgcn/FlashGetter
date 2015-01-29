package flashGetter.view.sidebar;

import javax.swing.ImageIcon;

import flashGetter.view.OptionPanel;
import flashGetter.view.ViewEvent;
import flashGetter.view.ViewEventDispatcher;
import flashGetter.view.ViewHandler;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class SideBarOptionPanel extends OptionPanel implements ViewHandler<SideBarOptionPanel>{
    
    private ViewEvent sideBarEvent;
    private boolean isChoosed;
    
    public SideBarOptionPanel(
            ImageIcon icon1,
            ImageIcon icon2,
            String option,
            ViewEvent event){
        this(icon1, icon2, option, event, false);
    }
    
    public SideBarOptionPanel(
            ImageIcon icon1,
            ImageIcon icon2,
            String option,
            ViewEvent event,
            boolean highlight){
        super(icon1, icon2, option, event);
        
        ViewEventDispatcher.InnerClass.instance.register(this);
        if(highlight) {
            lightComponent();
            isChoosed = true;
        }
        sideBarEvent = new ViewEvent().setTarget(SideBarOptionPanel.class);
        
    }
    
    @Override
    protected void mouseExited() {
        if(isChoosed) return;
        darkComponent();
    }
    
    @Override
    protected void mousePressed() {
        super.mousePressed();
        ViewEventDispatcher.InnerClass.instance.fireEvent(sideBarEvent);
        isChoosed = true;
        lightComponent();
    }

    @Override
    public void invoke(ViewEvent event) {
        isChoosed = false;
        darkComponent();
    }

    @Override
    public Class<SideBarOptionPanel> getGroupClass() {
        return SideBarOptionPanel.class;
    }
    

}