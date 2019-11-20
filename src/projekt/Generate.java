package projekt;

import java.util.ArrayList;

public class Generate {
    private int register_1_length;
    private int register_2_length;
    private int register_3_length;
    private ArrayList<Integer> register_1_gates;
    private ArrayList<Integer> register_2_gates;
    private ArrayList<Integer> register_3_gates;//= new ArrayList<>();
    private int function1Value;
    private int function2Value;
    private int function3Value;
    private String register_1_start;
    private String register_2_start;
    private String register_3_start;
    Generate(){
        function1Value=0;
    }
    void setRegister_1_length(int register_1_length) {
        this.register_1_length = register_1_length;
    }

    void setFunctionValue(int functionValue) {
        this.function1Value = functionValue;
    }
    void setFunction2Value(int functionValue) {
        this.function2Value = functionValue;
    }
    void setFunction3Value(int functionValue) {
        this.function3Value = functionValue;
    }

    void setRegister_2_length(int register_2_length) {
        this.register_2_length = register_2_length;
    }

    void setRegister_3_length(int register_3_length) {
        this.register_3_length = register_3_length;
    }

    void setRegister_1_gates(ArrayList<Integer> register_1_gates) {
        this.register_1_gates = register_1_gates;
    }

    void setRegister_2_gates(ArrayList<Integer> register_2_gates) {
        this.register_2_gates = register_2_gates;
    }

    void setRegister_3_gates(ArrayList<Integer> register_3_gates) {
        this.register_3_gates = register_3_gates;
    }

    void setRegister_1_start(String register_1_start) {
        this.register_1_start = register_1_start;
    }
    void setRegister_2_start(String register_2_start) {
        this.register_2_start = register_2_start;
    }
    void setRegister_3_start(String register_3_start) {
        this.register_3_start = register_3_start;
    }

    private String lfsr1;
    String generate(int n){
        StringBuilder lfsr1_builder = new StringBuilder();
        int newValue;
        int sum=0;
        ArrayList<Integer> register_1_array = new ArrayList<>();
        ArrayList<Integer> register_2_array = new ArrayList<>();
        ArrayList<Integer> register_3_array = new ArrayList<>();
        StringBuilder whole_code = new StringBuilder();
        //zapisanie do tablicy wartosci poczatkowych rejestru
        for(int i=0; i<register_1_length;i++){
            register_1_array.add(Character.getNumericValue(register_1_start.charAt(i)));
            lfsr1_builder.append(register_1_start.charAt(i));
        }
        for (int i=0,k=0;i<n;i++,k++) {
            sum = 0;
            for (Integer register_1_gate : register_1_gates) {
                sum = sum + register_1_array.get(register_1_array.size() - 1 - register_1_gate);
            }
            sum = sum+function1Value;
            newValue = sum % 2;
            register_1_array.remove(0);
            register_1_array.add(newValue);
            lfsr1_builder.append(register_1_array.get(register_1_length-1));
        }
        lfsr1 = lfsr1_builder.toString();
        System.out.println(lfsr1);
        //generowanie lfsr2, lfsr3
        for(int i=0; i<register_2_length;i++){
            register_2_array.add(Character.getNumericValue(register_2_start.charAt(i)));
        }
        for(int i=0; i<register_3_length;i++){
            register_3_array.add(Character.getNumericValue(register_3_start.charAt(i)));
        }
        int newValue2 = 0, newValue3 = 0;
        for(int i=0,j=0;i<n;i++,j++){
            if(j==register_1_length) j = 0;
            int liczba = (register_2_array.get(0)+register_3_array.get(0))%2;
            whole_code.append(liczba);
            if(lfsr1.charAt(i)=='1'){
                newValue2=lfsr23(register_2_gates, function2Value,register_2_array);
                register_2_array.remove(0);
                register_2_array.add(newValue2);
            }
            else if (lfsr1.charAt(i)=='0'){
                newValue3=lfsr23(register_3_gates, function3Value,register_3_array);
                register_3_array.remove(0);
                register_3_array.add(newValue3);
            }
        }
        return whole_code.toString();
    }
    private int lfsr23(ArrayList<Integer> register_gates, int functionValue, ArrayList<Integer> register_array){
        int newValue;
        int sum=0;
        for (Integer register_gate : register_gates) {
            sum = sum + register_array.get(register_array.size() - 1 - register_gate);
        }
        sum = sum+functionValue;
        newValue = sum % 2;
        return newValue;
    }
}

