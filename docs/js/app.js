function box(title, short, year, is_full, pdf, source) {

    status    = (is_full && pdf != "") ? "status-ok" : "status-fail"; 

    pdf_item    = "<a class=\"btn-good\" href=\"" + ((pdf != "") ? pdf : "#") + "\"><button type=\"button\" class=\"btn btn-sm btn-outline-secondary " + status +  "\">PDF</button></a>";
    source_item = "<a class=\"btn-good\" href=\"" + ((source != "") ? source : "#") + "\"><button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">Source</button></a>";

    return "\
    <div class=\"col-md-4\">\
        <div class=\"card mb-4 shadow-sm\">\
            <div class=\"card-body\">\
            <h1>" + short + "</h1>\
            <p class=\"card-text\">" + title + "</p>\
            <div class=\"d-flex justify-content-between align-items-center\">\
            <div class=\"btn-group\">\
                " + pdf_item + "\
                " + source_item + "\
            </div>\
            <small class=\"text-muted\" id=\"card-year\">" + year + " ANNO</small>\
            </div>\
            </div>\
        </div>\
    </div>";

}



var anno1 = [
    {"title": "Algebra lineare e Geometria", "short": "Algebra", "year": "I", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Algebra%20Lineare%20e%20Geometria/Algebra-geometria.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Primo%20Anno/Algebra%20Lineare%20e%20Geometria"},
    {"title": "Algoritmi e Strutture Dati", "short": "Algo1", "year": "I", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Algoritmi%20e%20Strutture%20Dati/algoritmi.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Primo%20Anno/Algoritmi%20e%20Strutture%20Dati"},
    {"title": "Analisi Matematica", "short": "Analisi", "year": "I", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Analisi%20Matematica/analisi.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Primo%20Anno/Analisi%20Matematica"},
    {"title": "Fondamenti di Informatica", "short": "Info", "year": "I", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Fondamenti/appunti.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Primo%20Anno/Fondamenti"}
]
var anno2 = [
    {"title": "Linguaggi di Programmazione", "short": "LP", "year": "II", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Linguaggi%20di%20Programmazione/linguaggi-di-programmazione.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Linguaggi%20di%20Programmazione"},
    {"title": "Linguaggi e Computabilità", "short": "LC", "year": "II", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Linguaggi%20e%20Computabilit%C3%A0/linguaggi-e-computabilit%C3%A0.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Linguaggi%20e%20Computabilit%C3%A0"},
    {"title": "Metodi algebrici per l'Informatica", "short": "Metodi", "year": "II", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Metodi%20algebrici%20per%20l'informatica/metodi-algebrici-per-informatica.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Metodi%20algebrici%20per%20l'informatica"},
    {"title": "Probabilità e Statistica per l'Informatica", "short": "ProbStat", "year": "II", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Probabilit%C3%A0%20e%20Statistica%20per%20l'Informatica/statistica.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Probabilit%C3%A0%20e%20Statistica%20per%20l'Informatica"},
    {"title": "Sistemi Distribuiti", "short": "SD", "year": "II", "full": true, "pdf": "", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Sistemi%20Distribuiti"},
    {"title": "Reti e Sistemi Operativi", "short": "RSO", "year": "II", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Reti%20e%20Sistemi%20Operativi/rso.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Reti%20e%20Sistemi%20Operativi"}
]
var anno3 = [
    {"title": "Analisi e progetto di Algoritmi", "short": "Algo2", "year": "III", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Analisi%20e%20Progetto%20di%20Algoritmi/algoritmi2.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Analisi%20e%20Progetto%20di%20Algoritmi"},
    {"title": "Elementi di Bioinformatica", "short": "BioInfo", "year": "III", "full": false, "pdf": "", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Elementi%20di%20Bioinformatica"},
    {"title": "Metodi Formali", "short": "Metodi", "year": "III", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Metodi%20Formali/metodi.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Metodi%20Formali"},
    {"title": "Ricerca Operativa e Pianificazione delle risorse", "short": "RO", "year": "III", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Ricerca%20Operativa%20e%20Pianificazione%20delle%20Risorse/ro.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Ricerca%20Operativa%20e%20Pianificazione%20delle%20Risorse"},
    {"title": "Programmazione C++", "short": "C++", "year": "III", "full": true, "pdf": "", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Programmazione%20C%2B%2B"}
]
var anno4 = [
    {"title": "Architetture Dati", "short": "Archi", "year": "IV", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Magistrale/Primo%20Anno/Architetture%20Dati/archid.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Magistrale/Primo%20Anno/Architetture%20Dati"},
    {"title": "Machine Learning", "short": "ML", "year": "IV", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Magistrale/Primo%20Anno/Machine%20Learning/ml.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Magistrale/Primo%20Anno/Machine%20Learning"},
    {"title": "Modelli della Concorrenza", "short": "Modelli", "year": "IV", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Magistrale/Primo%20Anno/Modelli%20della%20Concorrenza/modelli.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Magistrale/Primo%20Anno/Modelli%20della%20Concorrenza"},
    {"title": "Teoria della Computazione", "short": "Teoria", "year": "IV", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Magistrale/Primo%20Anno/Teoria%20della%20Computazione/computazione.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Magistrale/Primo%20Anno/Teoria%20della%20Computazione"},
    {"title": "Processo e Sviluppo del Software", "short": "PSS", "year": "IV", "full": true, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Magistrale/Primo%20Anno/Processo%20e%20Sviluppo%20Software/processo.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Magistrale/Primo%20Anno/Processo%20e%20Sviluppo%20Software"},
    {"title": "Teoria dell'Informazione e Crittografia", "short": "Critto", "year": "IV", "full": false, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Magistrale/Primo%20Anno/Teoria%20dell'Informazione%20e%20Crittografia/critto.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Magistrale/Primo%20Anno/Teoria%20dell'Informazione%20e%20Crittografia"},
    {"title": "Modelli Probabilistici per le Decisioni", "short": "ModProb", "year": "IV", "full": false, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Magistrale/Primo%20Anno/Modelli%20Probabilistici%20per%20le%20Decisioni/modprob.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Magistrale/Primo%20Anno/Modelli%20Probabilistici%20per%20le%20Decisioni"},
    {"title": "Bioinformatica", "short": "BioInfo", "year": "IV", "full": false, "pdf": "https://github.com/dlcgold/Appunti/raw/master/Magistrale/Primo%20Anno/Bioinformatica/bio.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Magistrale/Primo%20Anno/Bioinformatica"}
]
var anno5 = []

anno1.forEach(element => {
    document.getElementById("anno1").innerHTML += box(element.title, element.short, element.year, element.full, element.pdf, element.source)
});
anno2.forEach(element => {
    document.getElementById("anno2").innerHTML += box(element.title, element.short, element.year, element.full, element.pdf, element.source)
});
anno3.forEach(element => {
    document.getElementById("anno3").innerHTML += box(element.title, element.short, element.year, element.full, element.pdf, element.source)
});
anno4.forEach(element => {
    document.getElementById("anno4").innerHTML += box(element.title, element.short, element.year, element.full, element.pdf, element.source)
});
anno5.forEach(element => {
    document.getElementById("anno5").innerHTML += box(element.title, element.short, element.year, element.full, element.pdf, element.source)
});
