public class BloomFilter
{
      public int filter_len;
      private int filter;

      public BloomFilter(int f_len)
      {
        filter_len = f_len;
        filter = 0;
      }

      // хэш-функции
      public int hash1(String str1)
      {
        int code = 0;
        for (int i = 0; i < str1.length(); i++) {
            code = (17 * code + (int) str1.charAt(i)) % filter_len;
        }
         return code;
      }
      public int hash2(String str1)
      {
        int code = 0;
        for (int i = 0; i < str1.length(); i++) {
            code = (223 * code + (int) str1.charAt(i)) % filter_len;
        }
        return code;        
      }

      public void add(String str1)
      {
        filter |= (1 << hash1(str1));
        filter |= (1 << hash2(str1));
      }

      public boolean isValue(String str1)
      {
        int hash1Str = hash1(str1);
        int hash2Str = hash2(str1);
        System.out.println((filter & (1 << hash1Str)) >> hash1Str);
        System.out.println((filter & (1 << hash2Str)) >> hash2Str);
        if (((filter & (1 << hash1Str)) >> hash1Str) == 0
                || ((filter & (1 << hash2Str)) >> hash2Str) == 0) {
            return false;
        }
        return true;
      }
      
      public int getFilter() {
        return filter;
      }
}
