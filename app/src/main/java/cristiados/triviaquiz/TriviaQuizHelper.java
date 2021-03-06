package cristiados.triviaquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


class TriviaQuizHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TQuiz.db";
    private static final int DB_VERSION = 7;
    private static final String TABLE_NAME = "TQ";
    private static final String UID = "_UID";
    private static final String QUESTION = "QUESTION";
    private static final String OPTA = "OPTA";
    private static final String OPTB = "OPTB";
    private static final String OPTC = "OPTC";
    private static final String OPTD = "OPTD";
    private static final String ANSWER = "ANSWER";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    TriviaQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }


    public void delete(){
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete("TQ", null, null);
    }

    void allQuestion() {
        ArrayList<TriviaQuestion> arraylist = new ArrayList<>();
        arraylist.add(new TriviaQuestion("??Cu??ntos son los art??culos del CREDO?", "10", "6", "12", "8", "12"));
        arraylist.add(new TriviaQuestion("Honrar??s a tu padre y a tu madre, es el mandamiento n??mero:", "4", "3", "2", "1", "4"));
        arraylist.add(new TriviaQuestion("??C??mo le llamaba el San Gabriel Brochero a la Virgen Mar??a?", "La Pur??sima", "Virgencita", " La Sant??sima", "La Inmaculada", "La Pur??sima"));
        arraylist.add(new TriviaQuestion("C??mo sigue la oracion: Alma de Cristo...", "Santificame", "Salvame", "Confortame", "Lavame", "Santificame"));
        arraylist.add(new TriviaQuestion("??Cu??ntas son las Divinas Personas?", "3", "1", "2", "Ninguna", "3"));
        arraylist.add(new TriviaQuestion("??Cu??ntas veces se puede arrepentir un ??ngel?", "1", "Ninguna", "Depende", "Siempre", "Ninguna"));
        arraylist.add(new TriviaQuestion("??Qui??nes van al cielo? ", "Los que mueren en Gracia", "Los bautizados", "Los que creen en Dios", "Todos", "Los que mueren en Gracia"));
        arraylist.add(new TriviaQuestion("??Cuales son las virtudes teologales? ", "Fe, Caridad y Esperanza", "Amor, Justicia y Fe", "Solidaridad, Respeto y Humildad", "Libertad, igualdad y fraternidad", "Fe, Caridad y Esperanza"));
        arraylist.add(new TriviaQuestion("??Cuales son las virtudes Cardinales? ", "Templanza, Justicia, Prudencia y Fortaleza", "Amor, Templanza, Solidaridad y Tolerancia", "Paciencia, Respeto, Mansedumbre y Orgullo", "Valentia, Esfuerzo, Dedicaci??n y Astucia", "Templanza, Justicia, Prudencia y Fortaleza"));
        arraylist.add(new TriviaQuestion("No codiciar??s nada que sea de tu pr??jimo, es el mandamiento n??mero:", "4", "10", "8", "7", "10"));
        arraylist.add(new TriviaQuestion("??Los d??as s??bados se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Gozosos"));
        arraylist.add(new TriviaQuestion("??Los d??as domingos se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Gloriosos"));
        arraylist.add(new TriviaQuestion("??Los d??as viernes se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Dolorosos"));
        arraylist.add(new TriviaQuestion("??Los d??as jueves se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Luminosos"));
        arraylist.add(new TriviaQuestion("??Los d??as lunes se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Gozosos"));
        arraylist.add(new TriviaQuestion("??Los d??as martes se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Dolorosos"));
        arraylist.add(new TriviaQuestion("??Los d??as mi??rcoles se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Gloriosos"));
        arraylist.add(new TriviaQuestion("??Cu??l es el tercer misterio de luz... :", "El anuncio del Reino de Dios", "La Instituci??n de La Eucaristia", "El Bautismo de Jes??s", "El Nacimiento de Jes??s", "El anuncio del Reino de Dios"));
        arraylist.add(new TriviaQuestion("??Cu??l es el tercer misterio de dolor... :", "La Crucifixi??n", "La Coronaci??n de espinas", "La Flagelaci??n", "La Oraci??n de Jes??s en el huerto", "La Flagelaci??n"));
        arraylist.add(new TriviaQuestion("??Cu??l es el segundo misterio de gloria... :", "La Resurrecci??n", "Pentecostes", "La Ascenci??n", "La Asunci??n", "La Ascenci??n"));
        arraylist.add(new TriviaQuestion("Sagrado Coraz??n de Jes??s..", "En vos Conf??o", "Ten misericordia de nosotros", "Ruega por nosotros", "Amen", "En vos Conf??o"));
        arraylist.add(new TriviaQuestion("Sacratisimo Coraz??n de Jes??s..", "En vos Conf??o", "Ten misericordia de nosotros", "Ruega por nosotros", "Amen", "Ten misericordia de nosotros"));
        arraylist.add(new TriviaQuestion("??Cu??l es la quinta v??a de Santo Tomas de Aquino?", "El motor inm??vil", "El Orden de la Creaci??n", "La Causa Incausada", "El Ser Necesario", "El Orden de la Creaci??n"));
        arraylist.add(new TriviaQuestion("Ave Mar??a, gratia plena", "Fructus ventris", "Ora pro nobis", "Mortis nostrae", "Dominus tecum", "Dominus tecum"));
        arraylist.add(new TriviaQuestion("Dios te salve...", "Reina y Madre de misericordia", "A t?? llamamos", "??Oh, clement??sima, oh piadosa!", "Vuelve a nosotros esos tus ojos", "Reina y Madre de misericordia"));
        arraylist.add(new TriviaQuestion("??Cu??l es el tercer dolor de la Mater dolorosa... :", "La huida a Egipto", "La Crucifixi??n", "La sepultura de Cristo", "La perdida de Jes??s", "La perdida de Jes??s"));
        arraylist.add(new TriviaQuestion("??Cu??l de estos no es un pecado capital? ", "Ira", "Gula", "Lujuria", "Taca??er??a", "Taca??er??a"));
        arraylist.add(new TriviaQuestion("??Qu?? es la transubstanciaci??n?", "Cuando tu alma sube al cielo", "Cuando alguien se hace santo", "Cuando el Espirit?? Santo se tranform?? en paloma", "La conversi??n del pan y el vino en el Cuerpo y Sangre de Cristo", "La conversi??n del pan y el vino en el Cuerpo y Sangre de Cristo"));
        arraylist.add(new TriviaQuestion("??D??nde se encuentra la S??bana Santa? ", "En Tur??n", "En el Vaticano", "En Jerusal??n", "En Bel??n", "En Tur??n"));
        arraylist.add(new TriviaQuestion("La Iglesia Cat??lica dedica cada mes a una determinada devoci??n. ??Cu??l es la de octubre? ", "A la Sant??sima Trinidad", "A la Virgen del Rosario", "A San Jos??", "A las almas del purgatorio", "A la Virgen del Rosario"));
        arraylist.add(new TriviaQuestion("??C??mo se llama donde est?? escrita la Palabra de Dios?", "La Biblia", "El catecismo", "El diccionario", "Enciclica", "La Biblia"));
        arraylist.add(new TriviaQuestion("??Cu??l es la oraci??n que nos ense???? Jes??s?", "La salve", "El Padrenuestro", "El credo", "El Ave Mar??a", "El Padrenuestro"));
        arraylist.add(new TriviaQuestion("??Cu??ntos son los mandamientos?", "10", "12", "7", "8", "10"));
        arraylist.add(new TriviaQuestion("??Cu??l es el primer sacramento que tiene que recibir un cat??lico?", "La confirmaci??n", "El bautismo", "La penitencia", "El Orden Sagrado", "El bautismo"));
        arraylist.add(new TriviaQuestion("??Qu?? significa la palabra cat??lica?", "Creyente", "Universal", "Cristiano", "Carisma", "Universal"));
        arraylist.add(new TriviaQuestion("??Con qu?? otro nombre se conoce a los sacerdotes?", "Curas", "Seglares", "Laicos", "Fieles", "Curas"));
        arraylist.add(new TriviaQuestion("??En cuantas partes se divide la Biblia?", "Cuatro", "Dos", "Tres", "Una", "Dos"));
        arraylist.add(new TriviaQuestion("??Qu?? celebramos los cat??licos en Navidad?", "El nacimiento de Cristo", "La muerte de Cristo", "El a??o nuevo", "Las bodas de Can??", "El nacimiento de Cristo"));
        arraylist.add(new TriviaQuestion("??Qu?? es el Sagrario?", "Donde se guarda la Biblia", "Donde se guarda el cuerpo de Cristo", "La Iglesia", "Donde se lee la Biblia", "Donde se guarda el cuerpo de Cristo"));
        arraylist.add(new TriviaQuestion("??C??mo se llama el lugar sobre el que se celebra la Eucarist??a?", "Mesa", "Altar", "Reposorio", "Casa", "Altar"));
        arraylist.add(new TriviaQuestion("??Qu?? significa la palabra ??ngel?", "Bueno", "Mensajero", "Esp??ritu", "Inocente", "Mensajero"));
        arraylist.add(new TriviaQuestion("??Cu??l es el primer libro de la Biblia?", "El G??nesis", "Los Salmos", "El Apocalipsis", "El ??xodo", "El G??nesis"));
        arraylist.add(new TriviaQuestion("??Qu?? evangelios nos cuentan el nacimiento de Jes??s?", "Juan y Marcos", "Lucas y Mateo", "Juan y Mateo", "Todos", "Lucas y Mateo"));
        arraylist.add(new TriviaQuestion("??Seg??n la Biblia, qu?? era Babel?", "Un libro", "Una torre", "Un camello", "Un pa??s", "Una torre"));
        arraylist.add(new TriviaQuestion("??C??mo se llama el lugar donde crucificaron a Jes??s?", "Monte Carmelo", "R??o Jordan", "G??lgota", "Monte Athos", "G??lgota"));
        arraylist.add(new TriviaQuestion("??Qu?? significa la palabra cementerio en el idioma espa??ol?", "Enterramiento", "Dormitorio", "Fabrica de cemento", "Calvario", "Dormitorio"));
        arraylist.add(new TriviaQuestion("??Qu?? santos misioneros tradujeron los Evangelios a la lengua eslava?", "Cirilio y Metodio", "Pedro y Pablo", "Juan y Lucas", "Esteban y Bernab??", "Cirilio y Metodio"));
        arraylist.add(new TriviaQuestion("??Cu??l es el ??ltimo libro de la Biblia?", "El G??nesis", "Los Salmos", "El Apocalipsis", "El ??xodo", "El Apocalipsis"));
        arraylist.add(new TriviaQuestion("??Qu?? es el crisma?", "Aceite consagrado por el obispo", "Prenda de vestir", "Una cruz", "Un amuleto", "Aceite consagrado por el obispo"));
        arraylist.add(new TriviaQuestion("??Cu??l es el idioma del pueblo de Israel?", "Hebreo", "Griego", "Ingl??s", "??rabe", "Hebreo"));
        arraylist.add(new TriviaQuestion("??C??mo se llamaba el hijo de Abraham?", "Isaac", "Ismael", "Jacob", "Moises", "Isaac"));
        arraylist.add(new TriviaQuestion("??Cu??l es el nombre que se le da a Jacob en la Biblia?", "Isaac", "Ismael", "Israel", "Moises", "Israel"));
        arraylist.add(new TriviaQuestion("??Qu?? puesto de importancia ocup?? David en Israel?", "Emperador", "Rey", "Consejero", "Fara??n", "Rey"));
        arraylist.add(new TriviaQuestion("??Qu?? ciudad de Israel se le llamaba con bastante frecuencia Si??n?", "Jerusal??n", "Betania", "Jeric??", "N??nive", "Jerusal??n"));
        arraylist.add(new TriviaQuestion("??C??mo se llamaba el creador de la reforma protestante?", "Ulrico", "Calvino", "Mart??n Lutero", "Constantino I", "Mart??n Lutero"));
        arraylist.add(new TriviaQuestion("??C??mo se llama el fundador del Islam?", "Mahoma", "Buda", "Mart??n Lutero", "Constantino I", "Mahoma"));
        arraylist.add(new TriviaQuestion("??Qu?? hecho importante realiz?? Mois??s?", "Fund?? el pueblo de Israel", "Liber?? al pueblo de Egipto", "Se nombr?? fara??n del pueblo de Israel", "Construyo Bel??n", "Liber?? al pueblo de Egipto"));
        arraylist.add(new TriviaQuestion("??Qui??n es el sucesor de los ap??stoles?", "El obispo", "El cardenal", "El sacerdote", "El diacono", "El obispo"));
        arraylist.add(new TriviaQuestion("??Qu?? es un presb??tero?", "Un sacerdote", "Un laico", "Un fiel", "Un diacono", "Un sacerdote"));
        arraylist.add(new TriviaQuestion("??Qu?? d??a de la semana muri?? Jes??s?", "Lunes", "Jueves", "Viernes", "Martes", "Viernes"));
        arraylist.add(new TriviaQuestion("??Qu?? Papa convoc?? el Concilio Vaticano II?", "Juan XXIII", "Pablo VI", "Juan Pablo I", "Pio XII", "Juan XXIII"));
        arraylist.add(new TriviaQuestion("??Qu?? significa la palabra Am??n en castellano?", "Que sea as??", "Que se amen", "Que se perdonen", "Que sean uno", "Que sea as??"));
        arraylist.add(new TriviaQuestion("??Cu??ntos son los evangelios?", "Dos", "Cuatro", "Siete", "Tres", "Cuatro"));
        arraylist.add(new TriviaQuestion("??Cu??ntas tesis proclam?? Lutero?", "95", "73", "46", "7", "95"));
        arraylist.add(new TriviaQuestion("??En que libro de la Biblia se nos cuenta los primeros pasos de la Iglesia?", "G??nesis", "Hechos de los apostoles", "??xodo", "Eclesi??stico", "Hechos de los apostoles"));
        arraylist.add(new TriviaQuestion("??Cu??ntos eran los apostoles de Jes??s?", "Muchos", "Doce", "Once", "Tres", "Doce"));
        arraylist.add(new TriviaQuestion("??Qu?? fiesta celebraban los jud??os cuando muri?? y resucit?? Jes??s?", "La Pascua", "Pentecost??s", "El Yon Kippur", "Bar mitzvah", "La Pascua"));
        arraylist.add(new TriviaQuestion("??Qu?? pidi?? el rey Salom??n a Dios para poder gobernar a su pueblo?", "Poder", "Dinero", "Un templo", "Sabidur??a", "Sabidur??a"));
        arraylist.add(new TriviaQuestion("??Cu??les de estos NO es uno de los 7 dones del Espiritu Santo?", "Fortaleza", "Piedad", "Ciencia", "Audacia", "Audacia"));
        arraylist.add(new TriviaQuestion("??Cu??l fue el primer milagro que hizo Jes??s?", "Curar un enfermo", "Convertir agua en vino", "Multiplicar panes", "Caminar sobre el agua", "Convertir agua en vino"));
        arraylist.add(new TriviaQuestion("??Cu??ntos son los salmos?", "23", "150", "187", "200", "150"));


        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<TriviaQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (TriviaQuestion question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<TriviaQuestion> getAllOfTheQuestions() {

        List<TriviaQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            TriviaQuestion question = new TriviaQuestion();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
