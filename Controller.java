import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

/*The Controller class uses variables from the Model class in order to perform CRUD operations for the Medical Database Management System
it takes input from the GUI and displays the outputs there as well but also prints out results to console for bug testing purposes.
*/
class Controller{
    Model M1 = new Model();

    //Function to print entire main list
    public void printPeople() {
        //Iterates through main list and then prints the entire sub-list
        for(int i = 0; i < M1.People.size(); i++){
            //Takes item from main list for temporary use
            M1.Hold = (List<Object>) M1.People.get(i);
            System.out.println(M1.Hold);
        }
    }

    public void addManual(String name, String type, int age, float pressure, float height, float weight) {
        String patch;
        M1.real = 0;
        //Makes sure person is empty
        M1.Person.clear();
        //takes user input and puts into person
        System.out.println("Enter the person's name");
        M1.Person.add(name);
        System.out.println("Enter the person's blood type (A+ , A- , B+ , B- , AB+ , AB-, O+, O-)");
        M1.typC = type;
        //Checks if real blood type
        if(M1.typC.equals("A+") || M1.typC.equals("A-") || M1.typC.equals("B+") || M1.typC.equals("B-") || M1.typC.equals("AB+") || M1.typC.equals("AB-") || M1.typC.equals("O+") || M1.typC.equals("O-")){
            M1.Person.add(M1.typC);
        }
        else{
            System.out.println("That is not a valid blood type");
            return;
        }
        System.out.println("Enter the person's age (whole integers)");
        M1.ageC = age;
        //Makes sure value is at least 0
        if(M1.ageC >= 0){
            M1.Person.add(M1.ageC);
        }
        else{
            return;
        }
        System.out.println("Enter the person's blood pressure (number and decimal '102.2')");
        M1.prCh = pressure;
        if(M1.prCh >= 0){
            M1.Person.add(M1.prCh);
        }
        else{
            return;
        }
        System.out.println("Enter the person's height in inches (number and decimal '74.5')");
        M1.heiC = height;
        if(M1.heiC >= 0) {
            M1.Person.add(M1.heiC);
        }
        else{
            return;
        }
        System.out.println("Enter the person's weight in pounds (number and decimal '212.3')");
        M1.weiC = weight;
        if(M1.weiC >= 0){
            M1.Person.add(M1.weiC);
        }
        else{
            return;
        }
        //Compares the name and blood type of the new person with all the people inside the list
        for(int i = 0; i < M1.People.size(); i++){
            //Temporarily takes item from main list
            M1.Hold = (List<Object>) M1.People.get(i);
            System.out.println(M1.Hold + "\n" + M1.Person);
            if((M1.Hold.get(0).toString().equals(M1.Person.get(0).toString())) && (M1.Hold.get(1).toString().equals(M1.Person.get(1).toString()))){
                //If the name and blood type matches real is set to 1
                M1.real = 1;
                //Alerts user of any people that did not get added to list
                System.out.println(M1.Person.get(0) + " did not get added to people because that person already exists.");
            }
        }

        //If real hasn't been set to 1 because the name and blood type matches a copy of the person will be added to the list
        if(M1.real == 0){
            M1.People.add(M1.Person.clone());
        }
        //Empties person when done
        M1.Person.clear();
        //Prints everything in the newly updated list out
        printPeople();
    }

    //Adds people to list from file
    public void addFile(File path){
        try{
            //Initializes FileReader to take file
            FileReader fileReader = new FileReader(String.valueOf(path));
            //Initializes scanner to take from file
            Scanner scanner = new Scanner(fileReader);
            //Separates items by breaking them at the commas
            scanner.useDelimiter(",");
            //Ensures Person is empty
            M1.Person.clear();

            while (scanner.hasNextLine()) {
                M1.real = 0;
                //Takes data from file and adds it to Person
                Object data = scanner.next();
                M1.Person.add(data);
                data = scanner.next();
                M1.typC = String.valueOf(data);
                if(M1.typC.equals("A+") || M1.typC.equals("A-") || M1.typC.equals("B+") || M1.typC.equals("B-") || M1.typC.equals("AB+") || M1.typC.equals("AB-") || M1.typC.equals("O+") || M1.typC.equals("O-")){
                    M1.Person.add(M1.typC);
                }
                else{
                    M1.real = 1;
                }
                data = scanner.next();
                M1.ageC = Integer.parseInt(String.valueOf(data));
                if(M1.ageC >= 0){
                    M1.Person.add(M1.ageC);
                }
                else{
                    M1.real = 1;
                }
                data = scanner.next();
                M1.prCh = Float.parseFloat(String.valueOf(data));
                if(M1.prCh >= 0){
                    M1.Person.add(M1.prCh);
                }
                else{
                    M1.real = 1;
                }
                data = scanner.next();
                M1.heiC = Float.parseFloat(String.valueOf(data));
                if(M1.heiC >= 0){
                    M1.Person.add(M1.heiC);
                }
                else{
                    M1.real = 1;
                }
                //Takes data from file that doesn't end in a comma
                data = scanner.nextLine();
                String cut = data.toString();
                //Cuts off extra from data before entering it into person
                data = cut.replace(", ", "");
                M1.weiC = Float.parseFloat(String.valueOf(data));
                if(M1.weiC >= 0){
                    M1.Person.add(M1.weiC);
                }
                else{
                    M1.real = 1;
                }

                //Compares the name and blood type of the new person with all the people inside the list
                for(int i = 0; i < M1.People.size(); i++){
                    //Temporarily takes item from main list
                    M1.Hold = (List<Object>) M1.People.get(i);
                    if((M1.Hold.get(0).toString().equals(M1.Person.get(0).toString())) && (M1.Hold.get(1).toString().equals(M1.Person.get(1).toString()))){
                        //If the name and blood type matches real is set to 1
                        M1.real = 1;
                        //Alerts user of any people that did not get added to list
                        System.out.println(M1.Person.get(0) + " did not get added to people because the data was invalid.");
                    }
                }

                //If real hasn't been set to 1 because the ID matches a copy of the person will be added to the list
                if(M1.real == 0){
                    M1.People.add(M1.Person.clone());
                }
                else{
                    System.out.println(M1.Person.get(0) + " did not get added to people because that person already exists or is formated incorrectly.");
                }
                //Empties person when done
                M1.Person.clear();
            }
            scanner.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        //Prints everything in the newly updated list out
        printPeople();
    }

    public void removePerson(String name, String type){
        //User prompt
        System.out.println("Enter name of person to remove");
        //Takes user input
        M1.namL = name;
        System.out.println("Enter the person's blood type (A+ , A- , B+ , B- , AB+ , AB-, O+, O-)");
        M1.typL = type;
        if(!M1.typL.equals("A+") && !M1.typL.equals("A-") && !M1.typL.equals("B+") && !M1.typL.equals("B-") && !M1.typL.equals("AB+") && !M1.typL.equals("AB-") && !M1.typL.equals("O+") && !M1.typL.equals("O-")){
            System.out.println("That is not a valid blood type");
            return;
        }
        //Goes through list checking name and blood type
        for(int i = 0; i < M1.People.size(); i++){
            //Temporarily takes item from main list
            M1.Hold = (List<Object>) M1.People.get(i);
            //If the name and blood type entered are the same as any person in the list, the person is removed
            if((M1.Hold.get(0).toString().equals(M1.namL)) && (M1.Hold.get(1).toString().equals(M1.typL))){
                M1.NaDe = (String) M1.Hold.get(0);
                M1.People.remove(i);
                //Used to check if a person matched
                M1.check = 1;
            }
        }
        //Alerts user what book was removed
        if(M1.check == 1){
            System.out.println(M1.NaDe + " removed.");
            //Prints updated list
            printPeople();
        }
        //Notifies user if that ID does not exist
        else{
            System.out.println("This person does not exist");
        }
    }

    public void editPerson(String nameP, String typeP, String name, String type, String age, String pressure, String height, String weight){
        //User prompt
        System.out.println("Enter name of person to edit");
        //Takes user input
        M1.namL = nameP;
        System.out.println("Enter the person's blood type (A+ , A- , B+ , B- , AB+ , AB-, O+, O-)");
        M1.typL = typeP;
        if(!M1.typL.equals("A+") && !M1.typL.equals("A-") && !M1.typL.equals("B+") && !M1.typL.equals("B-") && !M1.typL.equals("AB+") && !M1.typL.equals("AB-") && !M1.typL.equals("O+") && !M1.typL.equals("O-")){
            System.out.println("That is not a valid blood type");
            return;
        }
        M1.Person.clear();
        //Goes through list checking name and blood type
        for(int i = 0; i < M1.People.size(); i++){
            //Temporarily takes item from main list
            M1.Hold = (List<Object>) M1.People.get(i);
            //If the name and blood type entered are the same as any person in the list, the person's location is put in loc
            if((M1.Hold.get(0).toString().equals(M1.namL)) && (M1.Hold.get(1).toString().equals(M1.typL))){
                M1.loc = i;
                //stores correct person
                for(int j = 0; j < M1.Hold.size(); j++){
                    M1.Person.add(M1.Hold.get(j));
                }
                //Used to check if a person matched
                M1.check = 1;
            }
        }
        //Alerts user what book was removed
        if(M1.check == 1){
            //takes new variables to replace person
            System.out.println("Enter the person's name");
            M1.namC = name;
            //if user leaves field empty the variable isn't changed
            if(!M1.namC.isEmpty()){
                M1.Person.set(0, M1.namC);
            }
            System.out.println("Enter the person's blood type (A+ , A- , B+ , B- , AB+ , AB-, O+, O-)");
            M1.typC = type;
            if(!M1.typC.isEmpty()){
                if(M1.typC.equals("A+") || M1.typC.equals("A-") || M1.typC.equals("B+") || M1.typC.equals("B-") || M1.typC.equals("AB+") || M1.typC.equals("AB-") || M1.typC.equals("0+") || M1.typC.equals("0-")){
                    M1.Person.set(1, M1.typC);
                }
                else{
                    System.out.println("That is not a valid blood type");
                    return;
                }
            }
            System.out.println("Enter the person's age (whole integers)");
            M1.ageE = age;
            if(!M1.ageE.isEmpty()){
                if(Integer.parseInt(String.valueOf(M1.ageE)) >= 0){
                    M1.Person.set(2, M1.ageE);
                }
            }
            System.out.println("Enter the person's blood pressure (number and decimal '102.2')");
            M1.prEd = pressure;
            if(!M1.prEd.isEmpty()){
                if(Float.parseFloat(String.valueOf(M1.prEd)) >= 0){
                    M1.Person.set(3, M1.prEd);
                }
            }
            System.out.println("Enter the person's height in inches (number and decimal '74.5')");
            M1.heiE = height;
            if(!M1.heiE.isEmpty()){
                if(Float.parseFloat(String.valueOf(M1.heiE)) >= 0){
                    M1.Person.set(4, M1.heiE);
                }
            }
            System.out.println("Enter the person's weight in pounds (number and decimal '212.3')");
            M1.weiE = weight;
            if(!M1.weiE.isEmpty()){
                if(Float.parseFloat(String.valueOf(M1.weiE)) >= 0){
                    M1.Person.set(5, M1.weiE);
                }
            }

            //replaces the person with the updated version
            M1.People.remove(M1.loc);
            M1.People.add(M1.Person.clone());
            //Prints updated list
            printPeople();
        }
        //Notifies user if that ID does not exist
        else{
            System.out.println("This person does not exist");
        }
    }

    public void getBMI(String name, String type){
        //User prompt
        System.out.println("Enter name of person to get bmi of");
        //Takes user input
        M1.namL = name;
        System.out.println("Enter blood type of person to get bmi of (A+ , A- , B+ , B- , AB+ , AB-, O+, O-)");
        M1.typL = type;
        if(!M1.typL.equals("A+") && !M1.typL.equals("A-") && !M1.typL.equals("B+") && !M1.typL.equals("B-") && !M1.typL.equals("AB+") && !M1.typL.equals("AB-") && !M1.typL.equals("O+") && !M1.typL.equals("O-")){
            System.out.println("That is not a valid blood type");
            return;
        }
        //Goes through list checking name and blood type
        for(int i = 0; i < M1.People.size(); i++){
            //Temporarily takes item from main list
            M1.Hold = (List<Object>) M1.People.get(i);
            //If the name and blood type entered are the same as any person in the list, the person's location is put in loc
            if((M1.Hold.get(0).toString().equals(M1.namL)) && (M1.Hold.get(1).toString().equals(M1.typL))){
                M1.loc = i;
                for(int j = 0; j <M1.Hold.size(); j++){
                    M1.Person.add(M1.Hold.get(j));
                }
                //Used to check if a book matched
                M1.check = 1;
            }
        }
        if(M1.check == 1){
            //Calculates and prints BMI
            M1.bmi = Float.parseFloat((M1.Person.get(5)).toString()) / (Float.parseFloat((M1.Person.get(4)).toString()) * Float.parseFloat((M1.Person.get(4)).toString())) * 703;
            System.out.println("The BMI of " + M1.Person.get(0) + " is " + M1.bmi);
        }
        else{
            System.out.println("This person does not exist");
        }
    }
}
