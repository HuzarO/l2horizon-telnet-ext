//  (version 17 : 61.0, super bit)
// Signature: Ll2/commons/data/xml/AbstractFileParser<Ll2/gameserver/data/xml/holder/ArmorSetsHolder;>;
public final class l2.gameserver.data.xml.parser.ArmorSetsParser extends l2.commons.data.xml.AbstractFileParser {
  
  // Field descriptor #195 Ll2/gameserver/data/xml/parser/ArmorSetsParser;
  private static final l2.gameserver.data.xml.parser.ArmorSetsParser llIl1lII;
  
  // Method descriptor #149 ()Ll2/gameserver/data/xml/parser/ArmorSetsParser;
  // Stack: 1, Locals: 0
  public static l2.gameserver.data.xml.parser.ArmorSetsParser getInstance();
    0  getstatic l2.gameserver.data.xml.parser.ArmorSetsParser.llIl1lII : l2.gameserver.data.xml.parser.ArmorSetsParser [47]
    3  areturn

  
  // Method descriptor #151 ()V
  // Stack: 2, Locals: 1
  private ArmorSetsParser();
    0  aload_0 [this]
    1  invokestatic l2.gameserver.data.xml.holder.ArmorSetsHolder.getInstance() : l2.gameserver.data.xml.holder.ArmorSetsHolder [68]
    4  invokespecial l2.commons.data.xml.AbstractFileParser(l2.commons.data.xml.AbstractHolder) [66]
    7  return

  
  // Method descriptor #141 ()Ljava/io/File;
  // Stack: 4, Locals: 1
  public java.io.File getXMLFile();
     0  new java.io.File [18]
     3  dup
     4  getstatic l2.gameserver.Config.DATAPACK_ROOT : java.io.File [45]
     7  ldc <String "data/armor_sets.xml"> [5]
     9  invokespecial java.io.File(java.io.File, java.lang.String) [48]
    12  areturn

  
  // Method descriptor #143 ()Ljava/lang/String;
  // Stack: 1, Locals: 1
  public java.lang.String getDTDFileName();
    0  ldc <String "armor_sets.dtd"> [3]
    2  areturn

  
  // Method descriptor #170 (Ljava/lang/String;)Ljava/util/Set;
  // Signature: (Ljava/lang/String;)Ljava/util/Set<Ll2/gameserver/templates/item/ItemTemplate;>;
  // Stack: 4, Locals: 6
  private java.util.Set I1l11lIllI(java.lang.String arg0);
     0  new java.util.HashSet [29]
     3  dup
     4  iconst_1
     5  invokespecial java.util.HashSet(int) [61]
     8  astore_2
     9  new java.util.StringTokenizer [34]
    12  dup
    13  aload_1 [arg0]
    14  ldc <String ";"> [1]
    16  invokespecial java.util.StringTokenizer(java.lang.String, java.lang.String) [63]
    19  astore_3
    20  aload_3
    21  invokevirtual java.util.StringTokenizer.hasMoreTokens() : boolean [64]
    24  ifeq 61
    27  aload_3
    28  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [65]
    31  invokestatic org.apache.commons.lang3.StringUtils.trimToEmpty(java.lang.String) : java.lang.String [80]
    34  astore 4
    36  invokestatic l2.gameserver.data.xml.holder.ItemHolder.getInstance() : l2.gameserver.data.xml.holder.ItemHolder [69]
    39  aload 4
    41  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [49]
    44  invokevirtual l2.gameserver.data.xml.holder.ItemHolder.getTemplate(int) : l2.gameserver.templates.item.ItemTemplate [70]
    47  astore 5
    49  aload_2
    50  aload 5
    52  invokeinterface java.util.Set.add(java.lang.Object) : boolean [85] [nargs: 2]
    57  pop
    58  goto 20
    61  aload_2
    62  areturn
    Stack map table: number of frames 2
        [pc: 20, full, stack: {}, locals: {_, _, java.util.HashSet, java.util.StringTokenizer}]
        [pc: 61, chop 1 local(s)]
  
  // Method descriptor #170 (Ljava/lang/String;)Ljava/util/Set;
  // Signature: (Ljava/lang/String;)Ljava/util/Set<Ll2/gameserver/model/Skill;>;
  // Stack: 5, Locals: 7
  private java.util.Set l1I1I1(java.lang.String arg0);
      0  new java.util.HashSet [29]
      3  dup
      4  iconst_1
      5  invokespecial java.util.HashSet(int) [61]
      8  astore_2
      9  new java.util.StringTokenizer [34]
     12  dup
     13  aload_1 [arg0]
     14  ldc <String ";"> [1]
     16  invokespecial java.util.StringTokenizer(java.lang.String, java.lang.String) [63]
     19  astore_3
     20  aload_3
     21  invokevirtual java.util.StringTokenizer.hasMoreTokens() : boolean [64]
     24  ifeq 124
     27  aload_3
     28  invokevirtual java.util.StringTokenizer.nextToken() : java.lang.String [65]
     31  invokestatic org.apache.commons.lang3.StringUtils.trimToEmpty(java.lang.String) : java.lang.String [80]
     34  astore 4
     36  aload 4
     38  invokevirtual java.lang.String.isEmpty() : boolean [53]
     41  ifeq 47
     44  goto 20
     47  aload 4
     49  bipush 45
     51  invokevirtual java.lang.String.indexOf(int) : int [52]
     54  istore 5
     56  iload 5
     58  iconst_1
     59  if_icmpge 81
     62  aload_0 [this]
     63  getfield l2.gameserver.data.xml.parser.ArmorSetsParser._log : org.slf4j.Logger [46]
     66  aload 4
     68  invokedynamic 0 makeConcatWithConstants(java.lang.String) : java.lang.String [90]
     73  invokeinterface org.slf4j.Logger.warn(java.lang.String) : void [89] [nargs: 2]
     78  goto 20
     81  invokestatic l2.gameserver.tables.SkillTable.getInstance() : l2.gameserver.tables.SkillTable [79]
     84  aload 4
     86  iconst_0
     87  iload 5
     89  invokevirtual java.lang.String.substring(int, int) : java.lang.String [55]
     92  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [49]
     95  aload 4
     97  iload 5
     99  iconst_1
    100  iadd
    101  invokevirtual java.lang.String.substring(int) : java.lang.String [54]
    104  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [49]
    107  invokevirtual l2.gameserver.tables.SkillTable.getInfo(int, int) : l2.gameserver.model.Skill [78]
    110  astore 6
    112  aload_2
    113  aload 6
    115  invokeinterface java.util.Set.add(java.lang.Object) : boolean [85] [nargs: 2]
    120  pop
    121  goto 20
    124  aload_2
    125  areturn
    Stack map table: number of frames 4
        [pc: 20, full, stack: {}, locals: {l2.gameserver.data.xml.parser.ArmorSetsParser, _, java.util.HashSet, java.util.StringTokenizer}]
        [pc: 47, append: {java.lang.String}]
        [pc: 81, append: {int}]
        [pc: 124, full, stack: {}, locals: {_, _, java.util.HashSet}]
  
  // Method descriptor #182 (Lorg/dom4j/Element;Ljava/util/Set;Ljava/lang/String;)V
  // Signature: (Lorg/dom4j/Element;Ljava/util/Set<Ll2/gameserver/templates/item/ItemTemplate;>;Ljava/lang/String;)V
  // Stack: 3, Locals: 5
  private void llIl1lII(org.dom4j.Element arg0, java.util.Set arg1, java.lang.String arg2);
     0  aconst_null
     1  aload_1 [arg0]
     2  aload_3 [arg2]
     3  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
     8  dup
     9  astore 4
    11  if_acmpeq 27
    14  aload_2 [arg1]
    15  aload_0 [this]
    16  aload 4
    18  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.I1l11lIllI(java.lang.String) : java.util.Set [73]
    21  invokeinterface java.util.Set.addAll(java.util.Collection) : boolean [86] [nargs: 2]
    26  pop
    27  return
    Stack map table: number of frames 1
        [pc: 27, full, stack: {}, locals: {}]
  
  // Method descriptor #181 (Lorg/dom4j/Element;)V
  // Stack: 10, Locals: 13
  protected void readData(org.dom4j.Element arg0) throws java.lang.Exception;
      0  aload_1 [arg0]
      1  ldc <String "set"> [13]
      3  invokeinterface org.dom4j.Element.elementIterator(java.lang.String) : java.util.Iterator [88] [nargs: 2]
      8  astore_2
      9  aload_2
     10  invokeinterface java.util.Iterator.hasNext() : boolean [81] [nargs: 1]
     15  ifeq 491
     18  new java.util.HashMap [28]
     21  dup
     22  invokespecial java.util.HashMap() [59]
     25  astore_3
     26  invokestatic java.util.Collections.emptySet() : java.util.Set [58]
     29  astore 4
     31  invokestatic java.util.Collections.emptySet() : java.util.Set [58]
     34  astore 5
     36  invokestatic java.util.Collections.emptySet() : java.util.Set [58]
     39  astore 6
     41  new java.util.HashMap [28]
     44  dup
     45  invokespecial java.util.HashMap() [59]
     48  astore 7
     50  new java.util.LinkedHashMap [31]
     53  dup
     54  invokespecial java.util.LinkedHashMap() [62]
     57  astore 8
     59  aload_2
     60  invokeinterface java.util.Iterator.next() : java.lang.Object [82] [nargs: 1]
     65  checkcast org.dom4j.Element [43]
     68  astore 9
     70  aload 9
     72  ldc <String "id"> [10]
     74  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
     79  invokestatic java.lang.Integer.parseInt(java.lang.String) : int [49]
     82  istore 10
     84  aload_0 [this]
     85  aload 9
     87  aload_3
     88  bipush 6
     90  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
     93  invokedynamic 1 apply() : java.util.function.Function [91]
     98  invokeinterface java.util.Map.computeIfAbsent(java.lang.Object, java.util.function.Function) : java.lang.Object [83] [nargs: 3]
    103  checkcast java.util.Set [33]
    106  ldc <String "chest"> [4]
    108  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.llIl1lII(org.dom4j.Element, java.util.Set, java.lang.String) : void [76]
    111  aload_0 [this]
    112  aload 9
    114  aload_3
    115  bipush 11
    117  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
    120  invokedynamic 1 apply() : java.util.function.Function [91]
    125  invokeinterface java.util.Map.computeIfAbsent(java.lang.Object, java.util.function.Function) : java.lang.Object [83] [nargs: 3]
    130  checkcast java.util.Set [33]
    133  ldc <String "legs"> [11]
    135  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.llIl1lII(org.dom4j.Element, java.util.Set, java.lang.String) : void [76]
    138  aload_0 [this]
    139  aload 9
    141  aload_3
    142  iconst_1
    143  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
    146  invokedynamic 1 apply() : java.util.function.Function [91]
    151  invokeinterface java.util.Map.computeIfAbsent(java.lang.Object, java.util.function.Function) : java.lang.Object [83] [nargs: 3]
    156  checkcast java.util.Set [33]
    159  ldc <String "head"> [9]
    161  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.llIl1lII(org.dom4j.Element, java.util.Set, java.lang.String) : void [76]
    164  aload_0 [this]
    165  aload 9
    167  aload_3
    168  bipush 10
    170  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
    173  invokedynamic 1 apply() : java.util.function.Function [91]
    178  invokeinterface java.util.Map.computeIfAbsent(java.lang.Object, java.util.function.Function) : java.lang.Object [83] [nargs: 3]
    183  checkcast java.util.Set [33]
    186  ldc <String "gloves"> [8]
    188  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.llIl1lII(org.dom4j.Element, java.util.Set, java.lang.String) : void [76]
    191  aload_0 [this]
    192  aload 9
    194  aload_3
    195  bipush 12
    197  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
    200  invokedynamic 1 apply() : java.util.function.Function [91]
    205  invokeinterface java.util.Map.computeIfAbsent(java.lang.Object, java.util.function.Function) : java.lang.Object [83] [nargs: 3]
    210  checkcast java.util.Set [33]
    213  ldc <String "feet"> [7]
    215  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.llIl1lII(org.dom4j.Element, java.util.Set, java.lang.String) : void [76]
    218  aload_0 [this]
    219  aload 9
    221  aload_3
    222  iconst_0
    223  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
    226  invokedynamic 1 apply() : java.util.function.Function [91]
    231  invokeinterface java.util.Map.computeIfAbsent(java.lang.Object, java.util.function.Function) : java.lang.Object [83] [nargs: 3]
    236  checkcast java.util.Set [33]
    239  ldc <String "underwear"> [17]
    241  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.llIl1lII(org.dom4j.Element, java.util.Set, java.lang.String) : void [76]
    244  aload 9
    246  ldc <String "shield"> [14]
    248  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
    253  ifnull 271
    256  aload_0 [this]
    257  aload 9
    259  ldc <String "shield"> [14]
    261  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
    266  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.I1l11lIllI(java.lang.String) : java.util.Set [73]
    269  astore 4
    271  aload 9
    273  ldc <String "skills"> [16]
    275  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
    280  ifnull 298
    283  aload_0 [this]
    284  aload 9
    286  ldc <String "skills"> [16]
    288  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
    293  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.l1I1I1(java.lang.String) : java.util.Set [75]
    296  astore 5
    298  aload 9
    300  ldc <String "shield_skills"> [15]
    302  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
    307  ifnull 325
    310  aload_0 [this]
    311  aload 9
    313  ldc <String "shield_skills"> [15]
    315  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
    320  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.l1I1I1(java.lang.String) : java.util.Set [75]
    323  astore 6
    325  iconst_1
    326  istore 11
    328  iload 11
    330  sipush 128
    333  if_icmpge 392
    336  aload 9
    338  ldc <String "enchant%dskills"> [6]
    340  iconst_1
    341  anewarray java.lang.Object [21]
    344  dup
    345  iconst_0
    346  iload 11
    348  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
    351  aastore
    352  invokestatic java.lang.String.format(java.lang.String, java.lang.Object[]) : java.lang.String [51]
    355  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
    360  astore 12
    362  aload 12
    364  ifnull 386
    367  aload 7
    369  iload 11
    371  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
    374  aload_0 [this]
    375  aload 12
    377  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.l1I1I1(java.lang.String) : java.util.Set [75]
    380  invokeinterface java.util.Map.put(java.lang.Object, java.lang.Object) : java.lang.Object [84] [nargs: 3]
    385  pop
    386  iinc 11 1
    389  goto 328
    392  iconst_1
    393  istore 11
    395  iload 11
    397  bipush 7
    399  if_icmpgt 458
    402  aload 9
    404  ldc <String "parts%dskills"> [12]
    406  iconst_1
    407  anewarray java.lang.Object [21]
    410  dup
    411  iconst_0
    412  iload 11
    414  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
    417  aastore
    418  invokestatic java.lang.String.format(java.lang.String, java.lang.Object[]) : java.lang.String [51]
    421  invokeinterface org.dom4j.Element.attributeValue(java.lang.String) : java.lang.String [87] [nargs: 2]
    426  astore 12
    428  aload 12
    430  ifnull 452
    433  aload 8
    435  iload 11
    437  invokestatic java.lang.Integer.valueOf(int) : java.lang.Integer [50]
    440  aload_0 [this]
    441  aload 12
    443  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.l1I1I1(java.lang.String) : java.util.Set [75]
    446  invokeinterface java.util.Map.put(java.lang.Object, java.lang.Object) : java.lang.Object [84] [nargs: 3]
    451  pop
    452  iinc 11 1
    455  goto 395
    458  aload_0 [this]
    459  invokevirtual l2.gameserver.data.xml.parser.ArmorSetsParser.getHolder() : l2.commons.data.xml.AbstractHolder [74]
    462  checkcast l2.gameserver.data.xml.holder.ArmorSetsHolder [37]
    465  new l2.gameserver.model.ArmorSet [40]
    468  dup
    469  iload 10
    471  aload_3
    472  aload 5
    474  aload 4
    476  aload 6
    478  aload 7
    480  aload 8
    482  invokespecial l2.gameserver.model.ArmorSet(int, java.util.Map, java.util.Set, java.util.Set, java.util.Set, java.util.Map, java.util.Map) [77]
    485  invokevirtual l2.gameserver.data.xml.holder.ArmorSetsHolder.addArmorSet(l2.gameserver.model.ArmorSet) : void [67]
    488  goto 9
    491  return
    Stack map table: number of frames 11
        [pc: 9, full, stack: {}, locals: {l2.gameserver.data.xml.parser.ArmorSetsParser, _, java.util.Iterator}]
        [pc: 271, full, stack: {}, locals: {l2.gameserver.data.xml.parser.ArmorSetsParser, _, java.util.Iterator, java.util.HashMap, java.util.Set, java.util.Set, java.util.Set, java.util.HashMap, java.util.LinkedHashMap, org.dom4j.Element, int}]
        [pc: 298, same]
        [pc: 325, same]
        [pc: 328, append: {int}]
        [pc: 386, same]
        [pc: 392, chop 1 local(s)]
        [pc: 395, append: {int}]
        [pc: 452, same]
        [pc: 458, full, stack: {}, locals: {l2.gameserver.data.xml.parser.ArmorSetsParser, _, java.util.Iterator, java.util.HashMap, java.util.Set, java.util.Set, java.util.Set, java.util.HashMap, java.util.LinkedHashMap, _, int}]
        [pc: 491, full, stack: {}, locals: {}]
  
  // Method descriptor #162 (Ljava/lang/Integer;)Ljava/util/Set;
  // Stack: 2, Locals: 1
  private static synthetic java.util.Set I1l11lIllI(java.lang.Integer arg0);
    0  new java.util.HashSet [29]
    3  dup
    4  invokespecial java.util.HashSet() [60]
    7  areturn

  
  // Method descriptor #151 ()V
  // Stack: 2, Locals: 0
  static {};
     0  new l2.gameserver.data.xml.parser.ArmorSetsParser [39]
     3  dup
     4  invokespecial l2.gameserver.data.xml.parser.ArmorSetsParser() [71]
     7  putstatic l2.gameserver.data.xml.parser.ArmorSetsParser.llIl1lII : l2.gameserver.data.xml.parser.ArmorSetsParser [47]
    10  return

  Inner classes:
    [inner class info: #25 java/lang/invoke/MethodHandles$Lookup, outer class info: #24 java/lang/invoke/MethodHandles
     inner name: #196 Lookup, accessflags: 25 public static final]
Bootstrap methods:
  0 : # 93 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#2 Unknown skill: ,
  1 : # 92 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
	Method arguments:
		#140 (Ljava/lang/Object;)Ljava/lang/Object;
		#94 invokestatic l2/gameserver/data/xml/parser/ArmorSetsParser.I1l11lIllI:(Ljava/lang/Integer;)Ljava/util/Set;
		#139 (Ljava/lang/Integer;)Ljava/util/Set;
}