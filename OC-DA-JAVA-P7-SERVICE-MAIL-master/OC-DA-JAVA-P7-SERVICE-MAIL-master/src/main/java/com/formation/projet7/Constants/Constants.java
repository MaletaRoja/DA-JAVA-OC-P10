package com.formation.projet7.Constants;

public class Constants {

	// adresse du compte expéditeur:  
    public static final String BIBLIO_EMAIL = "kintelalpz@gmail.com";
 
    // password compte expéditeur
    
    public static final String BIBLIO_PASSWORD = "bkppkhnxxpmhlinj";
 
    //  adresse destinataire (pour test)
    public static final String USER_EMAIL = "kintela@hotmail.fr";
    
    public static final String OBJET = "Bibliothèque municipale - relance";
    
    public static final long HEURES = 24;  			 // Mode production - durée cycle de scutation
    public static final long DELAY_AVIS= 2; 		 // Mode production - délai validité avant annulation réservation
    public static final long DELAY_AVIS_MIN = 12;	 // Mode démo - délai validité avant annulation réservation
	public static final long MINS = 2;				 // Mode démo - durée cycle de scrutation

}
