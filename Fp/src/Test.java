import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("---------------------------Main menu-----------------------------\n\n");
			System.out.println("Please choose one number:\n1.customer\n2.staff\n3.product\n4.exit\n\n");
			System.out.println("---------------------------Main menu-----------------------------\n\n");
			int option1 = Integer.parseInt(input.nextLine());
			if(option1 == 1){
				DisplayCustmoer();continue;
			}else if(option1 == 2){
				DisplayStaff();continue;
			}else if(option1 == 3){
				DisplayProduct();continue;
			}else if(option1 == 4){
				System.exit(0);
			}else{
				System.out.println("Wrong number");continue;
			}			
		}

	}


	private static void DisplayProduct() {
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("--------------------------Product menu---------------------------\n\n");
			System.out.println("Please choose one number:\n1.search the product\n2.shopping cart\n3.place order\n4.return\n\n");
			System.out.println("--------------------------Product menu---------------------------\n\n");
			int option = Integer.parseInt(input.nextLine());
			if(option == 1){
				SearchProduct.SearchProductMain();continue;
			}else if(option == 2){
				Shopping_Cart.ShoppingCartMain();continue;
			}else if(option == 3){
				Place_Order.Place_OrderMain();continue;
			}else if(option == 4){
				break;
			}else{
				System.out.println("Wrong number");continue;
			}
		}	
	}


	private static void DisplayCustmoer() {
		Scanner input = new Scanner(System.in);
		while(true){	
			boolean a = CheckCustomer1.CustomerLoginMain();
		if(a){
			System.out.println("The CusID is correct");
		}else{
			System.out.println("The CusID is not found");
			break;
			}
			System.out.println("---------------------------Customer menu-----------------------------\n\n");
			System.out.println("Please choose one number:\n1.address\n2.creditcard\n3.return\n");
			System.out.println("---------------------------Customer menu-----------------------------\n\n");
			int option = Integer.parseInt(input.nextLine());
			if(option == 1){
				Display_Address();continue;
			}else if(option == 2){
				Display_CreditCard();continue;
			}else if(option == 3){
				break;
			}else{
				System.out.println("Wrong number");continue;
			}	
		}
	}
	
	
	private static void Display_Address(){
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("-------------------customer address menu------------------------\n\n");
			System.out.println("Please choose one number:\n1.add an address\n2.delete an address\n"
					+ "3.modify an address\n4.return\n");
			System.out.println("-------------------customer address menu------------------------\n\n");
			int option = Integer.parseInt(input.nextLine());
			if(option == 1){
				AddAddress.AddAddressMain();
				break;
			}else if(option == 2){
				DeleteAddress.DeleteAddressMain();continue;
			}else if(option == 3){
				ModifyAddress.ModifyAddressmain();continue;
			}else if(option == 4){
				break;
			}else{
				System.out.println("Wrong number");continue;
			}
		}
	}
	
	
	private static void Display_CreditCard(){
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("-----------------------Credit card menu-------------------------\n\n");
			System.out.println("Please choose one number:\n1.add a creditcard\n2.delete a creditcard\n"
					+ "3.modify a creditcard\n4.return\n");
			System.out.println("-----------------------Credit card menu-------------------------\n\n");
			int option = Integer.parseInt(input.nextLine());
			if(option == 1){
				AddCreditCard.AddCreditCardMain();continue;
			}else if(option == 2){
				DeleteCreditCard.DeleteCreditCardMain();continue;
			}else if(option == 3){
				ModifyCreditCard.ModifyCreditCardMain();continue;
			}else if(option == 4){
				break;
			}else{
				System.out.println("Wrong number");continue;
			}
		}
	}
	
	
	private static void DisplayStaff() {
		Scanner input = new Scanner(System.in);
		while(true){
			boolean found = CheckStaff.StaffLoginMain();
			if(found){
				System.out.println("The staffID is correct");
			}else{
				System.out.println("The staff is not found");
				break;
				}
			System.out.println("---------------------------Staff menu-----------------------------\n\n");
			System.out.println("Please choose one number:\n1.product\n2.price\n3.stock\n4.return\n");
			System.out.println("---------------------------Staff menu-----------------------------\n\n");
			int option = Integer.parseInt(input.nextLine());
			if(option == 1){
				Display_Product();continue;
			}else if(option == 2){
				Display_Price();continue;
			}else if(option == 3){
				AddStock.AddStockMain();continue;
			}else if(option == 4){
				break;
			}else{
				System.out.println("Wrong number");continue;
			}	
		}
		
	}
	


	private static void Display_Product(){
		Scanner input = new Scanner(System.in);
		while(true){
			boolean found = CheckProduct.ProductIDStateCheckMain();
			if(found){
				System.out.println("This product exists in the database");
			}else{
				System.out.println("This product doesn't exist in the database");
				break;
				}
			System.out.println("----------------------Staff product menu-------------------------\n\n");
			System.out.println("Please choose one number:\n1.add a product\n2.delete a product\n3.modify a product\n4.return\n");
			System.out.println("----------------------Staff product menu-------------------------\n\n");
			int option = Integer.parseInt(input.nextLine());
			if(option == 1){
				AddProduct.AddProductMain();continue;
			}else if(option == 2){
				DeleteProduct.DeleteProductMain();continue;
			}else if(option == 3){
				ModifyProduct.ModifyProductMain();continue;
			}else if(option == 4){
				break;
			}else{
				System.out.println("Wrong number");continue;
			}
		}
	}
	
	private static void Display_Price(){
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("-----------------------Staff price menu-------------------------\n\n");
			System.out.println("Please choose one number:\n1.add a price\n2.delete a price\n3.modify a price\n4.return\n");
			System.out.println("-----------------------Staff price menu-------------------------\n\n");
			int option = Integer.parseInt(input.nextLine());
			if(option == 1){
				AddPrice.AddPriceMain();continue;
			}else if(option == 2){
				DeletePrice.DeletePriceMain();continue;
			}else if(option == 3){
				ModifyPrice.ModifyMain();continue;
			}else if(option == 4){
				break;
			}else{
				System.out.println("Wrong number");continue;
			}
		}
	}
	

}
