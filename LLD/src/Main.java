import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<List<String>> input = new ArrayList<>();
        input.add(Collections.singletonList("INFO [GET] [/api/user] [200] [120ms]"));
        input.add(Collections.singletonList("INFO [GET] [/api/order] [404] [120ms]"));
        input.add(Collections.singletonList("INFO [GET] [/api/user] [500] [100ms]"));
        input.add(Collections.singletonList("INFO [GET] [/api/order] [204] [120ms]"));
        input.add(Collections.singletonList("INFO [GET] [/api/order] [200] [120ms]"));
        input.add(Collections.singletonList("INFO [GET] [/api/user] [204] [120ms]"));
        input.add(Collections.singletonList("ERROR [GET] [/api/user] [404] [120ms]"));

        int apiPerUserCount = 0;
        int apiPerOrderCount = 0;

        int apiPerUserResponseTime = 0;
        int apiPerOrderResponseTime = 0;

        double apiPerUserErrorCount = 0.0;
        double apiPerOrderErrorCount = 0.0;

        int slowestApiPerUser = 10000;
        int slowestApiPerOrder = 10000;

        for(List<String> in : input){
            for(String s : in){
                String[] logs = s.split(" ");
                if(logs[2].equals("[/api/user]")){
                    apiPerUserCount++;
                    int currApiTime = Integer.parseInt(logs[4].substring(1,4));
                    apiPerUserResponseTime = apiPerUserResponseTime + currApiTime;
                    slowestApiPerUser = Math.min(slowestApiPerUser, currApiTime);
                    if(logs[0].contains("ERROR")) {
                        apiPerUserErrorCount = apiPerUserErrorCount + 1;
                    }

                } else {
                    apiPerOrderCount++;
                    int currApiTime = Integer.parseInt(logs[4].substring(1,4));
                    apiPerOrderResponseTime = apiPerOrderResponseTime + currApiTime;
                    slowestApiPerOrder = Math.min(slowestApiPerOrder, currApiTime);
                    if(logs[0].contains("ERROR")) {
                        apiPerOrderErrorCount = apiPerOrderErrorCount + 1;
                    }
                }
            }
        }

        System.out.println("Counts");
        System.out.println("/api/user=" + apiPerUserCount);
        System.out.println("/api/order=" + apiPerOrderCount);
        System.out.println("------------------");

        System.out.println("Average ResponseTime");
        System.out.println("/api/user:" + apiPerUserResponseTime/apiPerUserCount + "ms");
        System.out.println("/api/order:" + apiPerOrderResponseTime/apiPerOrderCount + "ms");
        System.out.println("------------------");

        System.out.println("Error ResponseTime");
        System.out.println("/api/user:" + apiPerUserErrorCount/apiPerUserCount + "%");
        System.out.println("/api/order:" + apiPerOrderErrorCount/apiPerOrderCount + "%");
        System.out.println("------------------");

        System.out.println("Slowest ResponseTime");
        System.out.println("/api/user:" + slowestApiPerUser + "ms");
        System.out.println("/api/order:" + slowestApiPerOrder + "ms");
        System.out.println("------------------");

    }
}