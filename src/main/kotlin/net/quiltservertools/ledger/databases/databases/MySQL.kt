package net.quiltservertools.ledger.databases.databases

import com.github.quiltservertools.ledger.Ledger
import com.mysql.cj.jdbc.MysqlDataSource
import net.quiltservertools.ledger.databases.DatabaseExtensionSpec
import java.nio.file.Path
import javax.sql.DataSource

object MySQL : LedgerDatabase {
    override fun getDataSource(savePath: Path): DataSource {
        var url = "jdbc:mysql://${Ledger.config[DatabaseExtensionSpec.url]}?rewriteBatchedStatements=true"
        for (arg in Ledger.config[DatabaseExtensionSpec.properties]) {
            url = url.plus("&$arg")
        }
        var db = MysqlDataSource()
        db.setURL(url)
        db.user = Ledger.config[DatabaseExtensionSpec.userName]
        db.password = Ledger.config[DatabaseExtensionSpec.password]
        return db
    }

    override fun getDatabaseIdentifier() = Ledger.identifier("mysql")
}
