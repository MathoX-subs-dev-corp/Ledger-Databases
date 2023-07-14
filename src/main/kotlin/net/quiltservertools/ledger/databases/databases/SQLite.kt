package net.quiltservertools.ledger.databases.databases

import com.github.quiltservertools.ledger.Ledger
import net.minecraft.server.MinecraftServer
import net.minecraft.util.WorldSavePath
import org.jetbrains.exposed.sql.Database
import org.sqlite.SQLiteDataSource
import java.nio.file.Path
import javax.sql.DataSource
import kotlin.io.path.pathString

object SQLite : LedgerDatabase {
    override fun getDataSource(savePath: Path): DataSource {
        val dbFilepath = savePath.resolve("ledger.sqlite").pathString
        return SQLiteDataSource().apply {
            url = "jdbc:sqlite:$dbFilepath"
        }
    }

    override fun getDatabaseIdentifier() = Ledger.identifier(Ledger.DEFAULT_DATABASE)
}
