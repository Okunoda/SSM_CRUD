import java.util.HashMap;

public class LeetCode1 {
    public int[] searchInt(int[] nums , int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i<nums.length ; i++){
            if(map.containsKey(target-nums[i])){
                int [] a = new int[2];
                a[0]=map.get(target-nums[i]);
                a[0]=map.get(i);
                return a;
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("nums empty");
    }
}
