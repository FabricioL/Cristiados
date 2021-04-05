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
        arraylist.add(new TriviaQuestion("¿Cuántos son los artículos del CREDO?", "10", "6", "12", "8", "12"));
        arraylist.add(new TriviaQuestion("Honrarás a tu padre y a tu madre, es el mandamiento número:", "4", "3", "2", "1", "4"));
        arraylist.add(new TriviaQuestion("¿Cómo le llamaba el San Gabriel Brochero a la Virgen María?", "La Purísima", "Virgencita", " La Santísima", "La Inmaculada", "La Purísima"));
        arraylist.add(new TriviaQuestion("Cómo sigue la oracion: Alma de Cristo...", "Santificame", "Salvame", "Confortame", "Lavame", "Santificame"));
        arraylist.add(new TriviaQuestion("¿Cuántas son las Divinas Personas?", "3", "1", "2", "Ninguna", "3"));
        arraylist.add(new TriviaQuestion("¿Cuántas veces se puede arrepentir un ángel?", "1", "Ninguna", "Depende", "Siempre", "Ninguna"));
        arraylist.add(new TriviaQuestion("¿Quiénes van al cielo? ", "Los que mueren en Gracia", "Los bautizados", "Los que creen en Dios", "Todos", "Los que mueren en Gracia"));
        arraylist.add(new TriviaQuestion("¿Cuales son las virtudes teologales? ", "Fe, Caridad y Esperanza", "Amor, Justicia y Fe", "Solidaridad, Respeto y Humildad", "Libertad, igualdad y fraternidad", "Fe, Caridad y Esperanza"));
        arraylist.add(new TriviaQuestion("¿Cuales son las virtudes Cardinales? ", "Templanza, Justicia, Prudencia y Fortaleza", "Amor, Templanza, Solidaridad y Tolerancia", "Paciencia, Respeto, Mansedumbre y Orgullo", "Valentia, Esfuerzo, Dedicación y Astucia", "Templanza, Justicia, Prudencia y Fortaleza"));
        arraylist.add(new TriviaQuestion("No codiciarás nada que sea de tu prójimo, es el mandamiento número:", "4", "10", "8", "7", "10"));
        arraylist.add(new TriviaQuestion("¿Los días sábados se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Gozosos"));
        arraylist.add(new TriviaQuestion("¿Los días domingos se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Gloriosos"));
        arraylist.add(new TriviaQuestion("¿Los días viernes se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Dolorosos"));
        arraylist.add(new TriviaQuestion("¿Los días jueves se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Luminosos"));
        arraylist.add(new TriviaQuestion("¿Los días lunes se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Gozosos"));
        arraylist.add(new TriviaQuestion("¿Los días martes se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Dolorosos"));
        arraylist.add(new TriviaQuestion("¿Los días miércoles se reza los misterios... :", "Luminosos", "Gloriosos", "Gozosos", "Dolorosos", "Gloriosos"));
        arraylist.add(new TriviaQuestion("¿Cuál es el tercer misterio de luz... :", "El anuncio del Reino de Dios", "La Institución de La Eucaristia", "El Bautismo de Jesús", "El Nacimiento de Jesús", "El anuncio del Reino de Dios"));
        arraylist.add(new TriviaQuestion("¿Cuál es el tercer misterio de dolor... :", "La Crucifixión", "La Coronación de espinas", "La Flagelación", "La Oración de Jesús en el huerto", "La Flagelación"));
        arraylist.add(new TriviaQuestion("¿Cuál es el segundo misterio de gloria... :", "La Resurrección", "Pentecostes", "La Ascención", "La Asunción", "La Ascención"));
        arraylist.add(new TriviaQuestion("Sagrado Corazón de Jesús..", "En vos Confío", "Ten misericordia de nosotros", "Ruega por nosotros", "Amen", "En vos Confío"));
        arraylist.add(new TriviaQuestion("Sacratisimo Corazón de Jesús..", "En vos Confío", "Ten misericordia de nosotros", "Ruega por nosotros", "Amen", "Ten misericordia de nosotros"));
        arraylist.add(new TriviaQuestion("¿Cuál es la quinta vía de Santo Tomas de Aquino?", "El motor inmóvil", "El Orden de la Creación", "La Causa Incausada", "El Ser Necesario", "El Orden de la Creación"));
        arraylist.add(new TriviaQuestion("Ave María, gratia plena", "Fructus ventris", "Ora pro nobis", "Mortis nostrae", "Dominus tecum", "Dominus tecum"));
        arraylist.add(new TriviaQuestion("Dios te salve...", "Reina y Madre de misericordia", "A tí llamamos", "¡Oh, clementísima, oh piadosa!", "Vuelve a nosotros esos tus ojos", "Reina y Madre de misericordia"));
        arraylist.add(new TriviaQuestion("¿Cuál es el tercer dolor de la Mater dolorosa... :", "La huida a Egipto", "La Crucifixión", "La sepultura de Cristo", "La perdida de Jesús", "La perdida de Jesús"));
        arraylist.add(new TriviaQuestion("¿Cuál de estos no es un pecado capital? ", "Ira", "Gula", "Lujuria", "Tacañería", "Tacañería"));
        arraylist.add(new TriviaQuestion("¿Qué es la transubstanciación?", "Cuando tu alma sube al cielo", "Cuando alguien se hace santo", "Cuando el Espiritú Santo se tranformó en paloma", "La conversión del pan y el vino en el Cuerpo y Sangre de Cristo", "La conversión del pan y el vino en el Cuerpo y Sangre de Cristo"));
        arraylist.add(new TriviaQuestion("¿Dónde se encuentra la Sábana Santa? ", "En Turín", "En el Vaticano", "En Jerusalén", "En Belén", "En Turín"));
        arraylist.add(new TriviaQuestion("La Iglesia Católica dedica cada mes a una determinada devoción. ¿Cuál es la de octubre? ", "A la Santísima Trinidad", "A la Virgen del Rosario", "A San José", "A las almas del purgatorio", "A la Virgen del Rosario"));
        arraylist.add(new TriviaQuestion("¿Cómo se llama donde está escrita la Palabra de Dios?", "La Biblia", "El catecismo", "El diccionario", "Enciclica", "La Biblia"));
        arraylist.add(new TriviaQuestion("¿Cuál es la oración que nos enseñó Jesús?", "La salve", "El Padrenuestro", "El credo", "El Ave María", "El Padrenuestro"));
        arraylist.add(new TriviaQuestion("¿Cuántos son los mandamientos?", "10", "12", "7", "8", "10"));
        arraylist.add(new TriviaQuestion("¿Cuál es el primer sacramento que tiene que recibir un católico?", "La confirmación", "El bautismo", "La penitencia", "El Orden Sagrado", "El bautismo"));
        arraylist.add(new TriviaQuestion("¿Qué significa la palabra católica?", "Creyente", "Universal", "Cristiano", "Carisma", "Universal"));
        arraylist.add(new TriviaQuestion("¿Con qué otro nombre se conoce a los sacerdotes?", "Curas", "Seglares", "Laicos", "Fieles", "Curas"));
        arraylist.add(new TriviaQuestion("¿En cuantas partes se divide la Biblia?", "Cuatro", "Dos", "Tres", "Una", "Dos"));
        arraylist.add(new TriviaQuestion("¿Qué celebramos los católicos en Navidad?", "El nacimiento de Cristo", "La muerte de Cristo", "El año nuevo", "Las bodas de Caná", "El nacimiento de Cristo"));
        arraylist.add(new TriviaQuestion("¿Qué es el Sagrario?", "Donde se guarda la Biblia", "Donde se guarda el cuerpo de Cristo", "La Iglesia", "Donde se lee la Biblia", "Donde se guarda el cuerpo de Cristo"));
        arraylist.add(new TriviaQuestion("¿Cómo se llama el lugar sobre el que se celebra la Eucaristía?", "Mesa", "Altar", "Reposorio", "Casa", "Altar"));
        arraylist.add(new TriviaQuestion("¿Qué significa la palabra ángel?", "Bueno", "Mensajero", "Espíritu", "Inocente", "Mensajero"));
        arraylist.add(new TriviaQuestion("¿Cuál es el primer libro de la Biblia?", "El Génesis", "Los Salmos", "El Apocalipsis", "El Éxodo", "El Génesis"));
        arraylist.add(new TriviaQuestion("¿Qué evangelios nos cuentan el nacimiento de Jesús?", "Juan y Marcos", "Lucas y Mateo", "Juan y Mateo", "Todos", "Lucas y Mateo"));
        arraylist.add(new TriviaQuestion("¿Según la Biblia, qué era Babel?", "Un libro", "Una torre", "Un camello", "Un país", "Una torre"));
        arraylist.add(new TriviaQuestion("¿Cómo se llama el lugar donde crucificaron a Jesús?", "Monte Carmelo", "Río Jordan", "Gólgota", "Monte Athos", "Gólgota"));
        arraylist.add(new TriviaQuestion("¿Qué significa la palabra cementerio en el idioma español?", "Enterramiento", "Dormitorio", "Fabrica de cemento", "Calvario", "Dormitorio"));
        arraylist.add(new TriviaQuestion("¿Qué santos misioneros tradujeron los Evangelios a la lengua eslava?", "Cirilio y Metodio", "Pedro y Pablo", "Juan y Lucas", "Esteban y Bernabé", "Cirilio y Metodio"));
        arraylist.add(new TriviaQuestion("¿Cuál es el último libro de la Biblia?", "El Génesis", "Los Salmos", "El Apocalipsis", "El Éxodo", "El Apocalipsis"));
        arraylist.add(new TriviaQuestion("¿Qué es el crisma?", "Aceite consagrado por el obispo", "Prenda de vestir", "Una cruz", "Un amuleto", "Aceite consagrado por el obispo"));
        arraylist.add(new TriviaQuestion("¿Cuál es el idioma del pueblo de Israel?", "Hebreo", "Griego", "Inglés", "Árabe", "Hebreo"));
        arraylist.add(new TriviaQuestion("¿Cómo se llamaba el hijo de Abraham?", "Isaac", "Ismael", "Jacob", "Moises", "Isaac"));
        arraylist.add(new TriviaQuestion("¿Cuál es el nombre que se le da a Jacob en la Biblia?", "Isaac", "Ismael", "Israel", "Moises", "Israel"));
        arraylist.add(new TriviaQuestion("¿Qué puesto de importancia ocupó David en Israel?", "Emperador", "Rey", "Consejero", "Faraón", "Rey"));
        arraylist.add(new TriviaQuestion("¿Qué ciudad de Israel se le llamaba con bastante frecuencia Sión?", "Jerusalén", "Betania", "Jericó", "Nínive", "Jerusalén"));
        arraylist.add(new TriviaQuestion("¿Cómo se llamaba el creador de la reforma protestante?", "Ulrico", "Calvino", "Martín Lutero", "Constantino I", "Martín Lutero"));
        arraylist.add(new TriviaQuestion("¿Cómo se llama el fundador del Islam?", "Mahoma", "Buda", "Martín Lutero", "Constantino I", "Mahoma"));
        arraylist.add(new TriviaQuestion("¿Qué hecho importante realizó Moisés?", "Fundó el pueblo de Israel", "Liberó al pueblo de Egipto", "Se nombró faraón del pueblo de Israel", "Construyo Belén", "Liberó al pueblo de Egipto"));
        arraylist.add(new TriviaQuestion("¿Quién es el sucesor de los apóstoles?", "El obispo", "El cardenal", "El sacerdote", "El diacono", "El obispo"));
        arraylist.add(new TriviaQuestion("¿Qué es un presbítero?", "Un sacerdote", "Un laico", "Un fiel", "Un diacono", "Un sacerdote"));
        arraylist.add(new TriviaQuestion("¿Qué día de la semana murió Jesús?", "Lunes", "Jueves", "Viernes", "Martes", "Viernes"));
        arraylist.add(new TriviaQuestion("¿Qué Papa convocó el Concilio Vaticano II?", "Juan XXIII", "Pablo VI", "Juan Pablo I", "Pio XII", "Juan XXIII"));
        arraylist.add(new TriviaQuestion("¿Qué significa la palabra Amén en castellano?", "Que sea así", "Que se amen", "Que se perdonen", "Que sean uno", "Que sea así"));
        arraylist.add(new TriviaQuestion("¿Cuántos son los evangelios?", "Dos", "Cuatro", "Siete", "Tres", "Cuatro"));
        arraylist.add(new TriviaQuestion("¿Cuántas tesis proclamó Lutero?", "95", "73", "46", "7", "95"));
        arraylist.add(new TriviaQuestion("¿En que libro de la Biblia se nos cuenta los primeros pasos de la Iglesia?", "Génesis", "Hechos de los apostoles", "Éxodo", "Eclesiástico", "Hechos de los apostoles"));
        arraylist.add(new TriviaQuestion("¿Cuántos eran los apostoles de Jesús?", "Muchos", "Doce", "Once", "Tres", "Doce"));
        arraylist.add(new TriviaQuestion("¿Qué fiesta celebraban los judíos cuando murió y resucitó Jesús?", "La Pascua", "Pentecostés", "El Yon Kippur", "Bar mitzvah", "La Pascua"));
        arraylist.add(new TriviaQuestion("¿Qué pidió el rey Salomón a Dios para poder gobernar a su pueblo?", "Poder", "Dinero", "Un templo", "Sabiduría", "Sabiduría"));
        arraylist.add(new TriviaQuestion("¿Cuáles de estos NO es uno de los 7 dones del Espiritu Santo?", "Fortaleza", "Piedad", "Ciencia", "Audacia", "Audacia"));
        arraylist.add(new TriviaQuestion("¿Cuál fue el primer milagro que hizo Jesús?", "Curar un enfermo", "Convertir agua en vino", "Multiplicar panes", "Caminar sobre el agua", "Convertir agua en vino"));
        arraylist.add(new TriviaQuestion("¿Cuántos son los salmos?", "23", "150", "187", "200", "150"));


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
