package model.component;

public abstract class Health {

    private int maxHealth;

    private int currentHealth;

    public Health(){
        this.maxHealth = getMaxHealth();
        this.currentHealth = maxHealth;
    }
    
    public abstract int getMaxHealth();

    public int getCurrentHealth() {
        return currentHealth;
    }

    public boolean isDead(){
        return currentHealth <= 0;
    }

    public boolean isFull(){
        return currentHealth >= maxHealth;
    }

    public void damage(int amount){
        currentHealth -= amount;
        if(isDead()){
            destroy();
        }
    }

    public abstract void destroy();

}
