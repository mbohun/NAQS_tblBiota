@Grab('com.xlson.groovycsv:groovycsv:1.3')
import static com.xlson.groovycsv.CsvParser.parseCsv

def final tbl_biota_csv_file = this.args[0]
println "tbl_biota_csv_file: ${tbl_biota_csv_file}"

def final map_per_line = parseCsv(new File(tbl_biota_csv_file).getText('utf-8'))
def final set_chrKingdomCode = [] as Set;
map_per_line.each {
    def final kc = it["chrKingdomCode"];
    if (kc) {
        set_chrKingdomCode << kc;
    } else {
        // kc == null
        println "ERROR (record with NO chrKingdomCode set): ${it}";
    }
}

def final NAQS_KINGDOM_NAMES = [
    "A": "Animalia",
    "B": "Bacteria",
    "F": "Fungi",
    "L": "Algae",
    "P": "Plantae",
    "V": "Viruses"
]

println "FOUND NAQS KINGDOMS:"
set_chrKingdomCode.each {
    def final kingdom_symbol = it
    def final kingdom_name =  NAQS_KINGDOM_NAMES[kingdom_symbol]
    println "${kingdom_symbol} (${kingdom_name})"
}
