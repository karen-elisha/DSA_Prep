class Solution {
    public boolean isPalindrome(String s) {
        String t="";
        for(int i=0;i<s.length();i++){
            char ch= s.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                t=t+Character.toLowerCase(ch);
            }
        }
        String r="";
        for(int i=t.length()-1;i>=0;i--){
             char ch= t.charAt(i);
            r=r+Character.toLowerCase(ch);
        }
    if(t.equals(r)){
        return true;
    }
    else
    return false;
    }
}