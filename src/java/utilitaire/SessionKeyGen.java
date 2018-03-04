
package utilitaire;

import java.util.UUID; //universally unique identifiers  

/**
 *
 * @author Mohammed Mehdi Sarray#
 * Cette classe est crée pour la generation de valeur unique de Session
 */
public class SessionKeyGen {
    
    UUID uuid = UUID.randomUUID(); // generation du ID 
    
    String randomUUIDString = uuid.toString(); 

    public SessionKeyGen() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getRandomUUIDString() {
        return randomUUIDString;
    }

    public void setRandomUUIDString(String randomUUIDString) {
        this.randomUUIDString = randomUUIDString;
    }
    
     
    
    
}
