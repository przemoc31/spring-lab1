package pg.przemek.lab1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLine implements CommandLineRunner
{
    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("Application started");
    }
}
