Trigger TriggerAccount on Account(After Insert){
	if(Trigger.isInsert){
		if(trigger.isAfter){
			AccountTriggerHandler.CreateContact(Trigger.New);
		}
	}
}
Public Class AccountTriggerHandler{
	public static void CreateContact(List<Account>listAccount){
		List<Contact> listContactUpdate = new List<Contact>();
		for(Account acc: listAccount){
			if(!listAccount.isEmpty()){
				if(acc.Create_Contact_Checkbox__c == true){
					Contact con = new Contact();
					con.LastName = 'Contact';
					con.FirstName = acc.Name;
					con.AccountId = acc.Id;
					listContactUpdate.add(con);
					
				}
			}
		}
		try{
			if(!listContactUpdate.isEmpty()){
			insert listContactUpdate;
		}
		}
		catch(Exception ex){
			system.addError('Error Message'+ex.getMessage());
		}
		
	}
}