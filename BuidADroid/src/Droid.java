public class Droid{

    String name;
    int batteryLevel;

    public String toString(){
        return "Hello, I’m the droid: "+name+", greetings.";
    }

    public Droid(String droidName){
        name = droidName;
        batteryLevel = 100;
    }

    public void performTask(String task){
        System.out.println(name+" is performing task: "+task);
        batteryLevel -= 90;
    }

    public void energyReport(){
        System.out.println("Battery level is: "+batteryLevel+"%");
    }

    public void energyTransfer(){
        batteryLevel += 20;
        System.out.println("Battery level charging...");
    }

    public static void main(String[] args){
        Droid droid1 = new Droid("Codey");
        System.out.println(droid1);
        droid1.performTask("cooking");
        droid1.energyReport();
        droid1.energyTransfer();
        droid1.energyTransfer();
        droid1.energyTransfer();
        droid1.energyReport();

    }
}