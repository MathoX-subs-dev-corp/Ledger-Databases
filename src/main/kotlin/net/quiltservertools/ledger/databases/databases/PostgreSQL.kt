package net.quiltservertools.ledger.databases.databases

import com.github.quiltservertools.ledger.Ledger
import net.minecraft.server.MinecraftServer
import net.quiltservertools.ledger.databases.DatabaseExtensionSpec
import org.jetbrains.exposed.sql.Database
import org.postgresql.ds.PGSimpleDataSource
import java.nio.file.Path
import javax.sql.DataSource

object PostgreSQL : LedgerDatabase {
    override fun getDataSource(savePath: Path): DataSource {
        var url = "jdbc:postgresql://${Ledger.config[DatabaseExtensionSpec.url]}?rewriteBatchedStatements=true"
        for (arg in Ledger.config[DatabaseExtensionSpec.properties]) {
            url = url.plus("&$arg")
        }
        val db = PGSimpleDataSource()
        db.setURL(url)
        db.user = Ledger.config[DatabaseExtensionSpec.userName]
        db.password = Ledger.config[DatabaseExtensionSpec.password]
        return db
    }

    override fun getDatabaseIdentifier() = Ledger.identifier("postgresql")
}
