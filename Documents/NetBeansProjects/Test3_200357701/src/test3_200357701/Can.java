/*
 * Student Name- Tanveer Kalia
 * Student Number- 200357701
 */
package test3_200357701;

/**
 *
 * @author tanve
 */
public class Can {
    private String productName;
    private int volume;

    public Can(String productName, int volume) {
        this.productName = productName;
        this.volume = volume;
    }

    public String getProductName() {
        return productName;
    }

    public int getVolume() {
        
        if (volume %1==0)
        return volume;
        else
            throw new IllegalArgumentException("The Volume should be a whole number and should be in ml");
        
                
        
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    
    
}
