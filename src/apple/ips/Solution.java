package apple.ips;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.getIps("12105918"));
        System.out.println(s.getIps("1201059189"));
        System.out.println(s.getIps("12104565918"));
        System.out.println(s.getIps("0000"));
        System.out.println(s.getIps("127000"));
    }

    public List<String> getIps(String digits, int ... level) {
        List<String> ipsl = new ArrayList<>();
        int lev = level.length > 0 ? level[0] : 0;
        int lastiv = -1;
        for(int i = 1; i<4; i++) {
            if(digits.length() >= i) {
                String d = digits.substring(0, i);
                int iv = Integer.parseInt(d);
                if(iv < 256 && lastiv != iv) {
                    lastiv = iv;
                    if(lev == 4) {
                        if(digits.substring(i).length() == 0) {
                            ipsl.add(String.valueOf(iv));
                        } else {
                            ipsl.add(null);
                        }
                    } else {
                        List<String> ips = getIps(digits.substring(i),lev+1);
                        if(ips.isEmpty()) {
                            ipsl.add(String.valueOf(iv));
                        } else if(!ips.contains(null)) {
                            ipsl.addAll(ips.stream().map(ip -> String.valueOf(iv) + "." + ip).collect(Collectors.toList()));
                        }
                    }
                }
            }
        }
        if (lev == 0) {
            return ipsl.stream().filter(ip -> ip.split("\\.").length == 4).collect(Collectors.toList());
        }
        return ipsl;
    }
}
