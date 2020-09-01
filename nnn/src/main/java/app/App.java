package app;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private static final String PROGRAM_NAME = "jacdump";
    private static final String PROGRAM_VERSION = "-2-0-0";

    private static final String PROGRAM_CMD = "java -jar";
    private static final String PROGRAM_SUFFIX = "-SNAPSHOT-jar-with-dependencies.jar";

    private static final String SEPARATOR = " ";

    private static final String F = "---";
    private static final String R = "###";
    private static final String C = ",";
    private static final String CONST_SIGN = "CCCCC";
    private static final String METHOD_SIGN = "MMMMM";
    private static final String CLASS_GRP_DIGIT = "%08d";
    private static final String CLASS_GRP_SEQ_DIGIT = "%04d";

    private static final String FS = "\t";
    private static final String RS = "\n";

    private static final String CLASSFILE_NAME = "クラス名";
    private static final String IS_CONSTANT_OR_METHOD_KEY_NAME = "定数かメソッドか";
    private static final String CLASS_NO = "クラス番号";
    private static final String CLASS_NAME = "クラス名";
    private static final String CLASS_SEQ_NO = "クラスシーケンス番号";
    private static final String CONSTANT_NAME = "定数名";
    private static final String ACCESS_PRIVILEGE = "アクセス修飾子";
    private static final String RETURN_TYPE = "戻り値の型";
    private static final String METHOD_NAME = "メソッド名";
    private static final String EXISTS_VARIABLE_ARGS = "可変長引数があるか";
    private static final String ARGS_CNT = "引数の個数";
    private static final String TYPE_PARAMETER_LIST = "型パラメータリスト";
    private static final String TYPE_PARAMETER_SIGN_LIST = "型パラメータ記号リスト";
    private static final String ARGS_TYPE_LIST = "引数の型リスト";
    private static final String ARGS_TYPE_VARIABLE_NAME_LIST = "仮引数の変数名リスト";

    private static final String OPTION_ARGUMENTS_CONSTANT = "--constant";
    private static final String OPTION_ARGUMENTS_METHOD = "--method";
    private static String DEFAULT_OUTPUT = OPTION_ARGUMENTS_CONSTANT;

    private static List<String> OPTION_USAGE_CLASSFILE_LIST = new LinkedList(){{
        add("java.lang.ClassLoader");
        add("java.lang.Thread");
    }};

    private static final List<String> OUTPUT_HEADER_COMMON_COLUMN_NAME_LIST = new LinkedList(){{
        add(CLASSFILE_NAME);
        add(IS_CONSTANT_OR_METHOD_KEY_NAME);
        add(CLASS_NO);
        add(CLASS_SEQ_NO);
        add(CLASS_NAME);
    }};

    private static final List<String> OUTPUT_HEADER_CONSTANT_COLUMN_NAME_LIST = new LinkedList(){{
        addAll(OUTPUT_HEADER_COMMON_COLUMN_NAME_LIST);
        add(CONSTANT_NAME);
    }};

    private static final List<String> OUTPUT_HEADER_METHOD_COLUMN_NAME_LIST = new LinkedList(){{
        addAll(OUTPUT_HEADER_COMMON_COLUMN_NAME_LIST);
        add(ACCESS_PRIVILEGE);
        add(RETURN_TYPE);
        add(METHOD_NAME);
        add(EXISTS_VARIABLE_ARGS);
        add(ARGS_CNT);
        add(TYPE_PARAMETER_LIST);
        add(TYPE_PARAMETER_SIGN_LIST);
        add(ARGS_TYPE_LIST);
        add(ARGS_TYPE_VARIABLE_NAME_LIST);
    }};

    private static List<List<String>> getClassInfo(Integer grp,Map<Class<?>,String> m ) {

        List<List<String>> rt = new LinkedList<>();

        for(Map.Entry<String,List<String>> entry : wrapperClassInfo(grp,m).entrySet()){

            rt.add(flattenList(Arrays.asList(entry.getKey().split(F)).stream().collect(Collectors.toList()),entry.getValue()));

        }

        return rt;
    }

    @SafeVarargs
    private static <E> List<E> flattenList(Collection<E>... liz){
        return Arrays.stream(liz).flatMap(e -> e.stream()).collect(Collectors.toList());
    }
    private static Map<Method,Class<?>> getMethodInfo(Class<?> e) throws NoClassDefFoundError, VerifyError, IncompatibleClassChangeError, InternalError{
        List<Method> l = Arrays.asList(e.getMethods());
        return IntStream.rangeClosed(0,l.size()-1).boxed().parallel().collect(Collectors.toMap(i->l.get(i),i->e));
    }
    private static Map<Field,Class<?>> getFieldInfo(Class<?> e) throws NoClassDefFoundError, VerifyError, IncompatibleClassChangeError, InternalError{
        List<Field> l = Arrays.asList(e.getFields());
        return IntStream.rangeClosed(0,l.size()-1).boxed().parallel().collect(Collectors.toMap(i->l.get(i),i->e));
    }
    private static Map<String,List<String>> wrapperFieldInfo(Integer grp,Map.Entry<Class<?>,String> entryClass,Class<?> clz)  throws NoClassDefFoundError, VerifyError, IncompatibleClassChangeError, InternalError{
        Map<String,List<String>> rt = new LinkedHashMap<>();
        int cnt = 0;
        for(Map.Entry<Field,Class<?>> entryField : getFieldInfo(clz).entrySet()){
            ++cnt;
            rt.put(entryClass.getValue()+F+CONST_SIGN+F+String.format(CLASS_GRP_DIGIT,grp)+F+String.format(CLASS_GRP_SEQ_DIGIT,cnt)
                    ,Arrays.asList(
                            entryField.getValue().getName() //クラス名
                            ,entryField.getKey().getName() //定数名
                    )
            );
        }
        return rt;
    }
    private static Map<String,List<String>> wrapperMethodInfo(Integer grp,Map.Entry<Class<?>,String> entryClass,Class<?> clz){
        Map<String,List<String>> rt = new LinkedHashMap<>();
        int cnt = 0;
        for(Map.Entry<Method,Class<?>> entryMethod : getMethodInfo(clz).entrySet()){
            ++cnt;
            rt.put(entryClass.getValue()+F+METHOD_SIGN+F+String.format(CLASS_GRP_DIGIT,grp)+F+String.format(CLASS_GRP_SEQ_DIGIT,cnt)
                    ,Arrays.asList(
                            entryMethod.getValue(). getName() //クラス名
                            ,Modifier.toString(entryMethod.getKey().getModifiers()) //アクセス修飾子
                            ,entryMethod.getKey().getGenericReturnType().getTypeName() //戻り値の型
                            ,entryMethod.getKey().getName() //メソッド名
                            ,String.valueOf(entryMethod.getKey().isVarArgs()) //可変長引数があるか
                            ,String.valueOf(entryMethod.getKey().getParameterCount()) //引数の個数
                            ,Arrays.stream(entryMethod.getKey().getTypeParameters()).flatMap(e->Arrays.asList(e.getBounds()).stream()).map(ee->ee.getTypeName().replace(C,R)).collect(Collectors.joining(C)) //型パラメータリスト
                            ,Arrays.stream(entryMethod.getKey().getTypeParameters()).map(e->e.getTypeName().replace(C,R)).collect(Collectors.joining(C)) //型パラメータで使用しているアルファベット大文字記号リスト
                            ,Arrays.stream(entryMethod.getKey().getGenericParameterTypes()).map(e->e.getTypeName().replace(C,R)).collect(Collectors.joining(C)) //引数の型リスト
                            ,Arrays.stream(entryMethod.getKey().getParameters()).map(e->e.getName()).collect(Collectors.joining(C)) //仮引数の変数名リスト
                    ));
        }
        return rt;
    }
    private static Map<String,List<String>> wrapperClassInfo(Integer grp,Map<Class<?>,String> classInfoMap){
        Map<String,List<String>> rt = new LinkedHashMap<>();
        for(Map.Entry<Class<?>,String> entryClass : classInfoMap.entrySet()){
            Class<?> clz = entryClass.getKey();
            rt.putAll(wrapperFieldInfo(grp,entryClass,clz));
            rt.putAll(wrapperMethodInfo(grp,entryClass,clz));
        }
        return rt;
    }

    private static void Usage(){
        System.out.println("Usageだよーん" +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + OPTION_ARGUMENTS_METHOD +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + OPTION_ARGUMENTS_METHOD + SEPARATOR + OPTION_USAGE_CLASSFILE_LIST.stream().collect(Collectors.joining(SEPARATOR)) +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + OPTION_ARGUMENTS_CONSTANT +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + OPTION_ARGUMENTS_CONSTANT + SEPARATOR + OPTION_USAGE_CLASSFILE_LIST.stream().collect(Collectors.joining(SEPARATOR)) +
                RS +
                RS +
                ""
        );
        System.exit(0);
    }

    public static void main(String... cmdLineArgs){

        List<String> cmdLineList = new LinkedList<>(Arrays.asList(cmdLineArgs));

        String classFileName = System.getProperty("sun.boot.library.path") + "/classlist";

        File file = new File(classFileName);

        List<String> classFileList = null;
        List<String> classFileSystemList = new LinkedList<>();
        List<String> classFileUserList = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));){

            String classFile;

            while ((classFile = reader.readLine()) != null) {

                classFileSystemList.add(classFile);

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        classFileList = classFileSystemList;

        //出力情報の制御
        for(String arg : cmdLineList){
            switch (arg){
                case OPTION_ARGUMENTS_CONSTANT:
                    DEFAULT_OUTPUT = OPTION_ARGUMENTS_CONSTANT;
                    break;
                case OPTION_ARGUMENTS_METHOD:
                    DEFAULT_OUTPUT = OPTION_ARGUMENTS_METHOD;
                    break;
                default:
                    classFileUserList.add(arg);
                    break;
            }
        }

        if(classFileUserList.size() != 0){
            classFileList = classFileUserList;
        }

        int cnt = classFileList.size();

        List<Map<Class<?>,String>> loadClassFileNameMapList = new LinkedList<>();

        Map<Class<?>,String> loadClassFileNameMap;

        List<String> classLoadList = new LinkedList<>();
        List<String> classLoadSkipList = new LinkedList<>();
        List<String> classExecuteList = new LinkedList<>();
        List<String> classExecuteSkipList = new LinkedList<>();

        for(int i = 0;i < cnt;i++){

            String classFile = classFileList.get(i);
            String className = classFile.replace('/', '.').replaceAll(".class$", "");

            try{

                Class<?> loadClass = classLoader.loadClass(className);

                loadClassFileNameMap = new LinkedHashMap(){{
                    put(loadClass,classFile);
                }};

                loadClassFileNameMapList.add(loadClassFileNameMap);

                classLoadList.add(className);

            }catch (ClassNotFoundException e){
                //実行時のハンドリング
                classLoadSkipList.add(className);
            }
        }

        int grp = 0;

        Map<String,List<List<String>>> classInfoMap = new LinkedHashMap<>();

        for(int i = 0;i < cnt;i++){

            try{

                grp++;

                classInfoMap.put(loadClassFileNameMapList.get(i).values().stream().findFirst().get(),getClassInfo(grp,loadClassFileNameMapList.get(i)));

                classExecuteList.add(loadClassFileNameMapList.get(i).values().stream().findFirst().get());

            }catch (Exception e){
                //実行時のハンドリング
                classExecuteSkipList.add(loadClassFileNameMapList.get(i).values().stream().findFirst().get());
            }
        }

        int n = 0;
        for(List<List<String>> summaryList :classInfoMap.values()){

            n++;

            if(n == 1){
                // ヘッダ行の出力

                if(DEFAULT_OUTPUT == OPTION_ARGUMENTS_CONSTANT){

                    System.out.println(OUTPUT_HEADER_CONSTANT_COLUMN_NAME_LIST.stream().collect(Collectors.joining(FS)));

                }else if(DEFAULT_OUTPUT == OPTION_ARGUMENTS_METHOD){

                    System.out.println(OUTPUT_HEADER_METHOD_COLUMN_NAME_LIST.stream().collect(Collectors.joining(FS)));

                }else{

                    Usage();

                }
            }

            for(List<String> detailList : summaryList){

                if(DEFAULT_OUTPUT == OPTION_ARGUMENTS_CONSTANT){

                    if(detailList.size()==OUTPUT_HEADER_CONSTANT_COLUMN_NAME_LIST.size()){
                        System.out.println(detailList.stream().collect(Collectors.joining(FS)));
                    }

                }else if(DEFAULT_OUTPUT == OPTION_ARGUMENTS_METHOD){

                    if(detailList.size()==OUTPUT_HEADER_METHOD_COLUMN_NAME_LIST.size()){
                        System.out.println(detailList.stream().collect(Collectors.joining(FS)));
                    }

                }else{

                    Usage();

                }
            }
        }

        System.err.printf(
                "%s" + FS + "%s" + RS +
                "%s" + FS + "%s" + RS +
                "%s" + FS + "%s" + RS +
                "%s" + FS + "%s" + RS +
                RS
                ,"classLoadDoneCnt",classLoadList.size()
                ,"classLoadSkipCnt",classLoadSkipList.size()
                ,"classExecuteDoneCnt",classExecuteList.size()
                ,"classExecuteSkipCnt",classExecuteSkipList.size()
        );

        System.err.printf(
                "%s" + FS + "%s" + RS
                ,"classExecuteSkipList",classExecuteSkipList
        );
    }
}
