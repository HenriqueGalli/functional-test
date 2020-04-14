package br.com.hgalli.tasks.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"),cap);
		driver.navigate().to("http://192.168.0.15:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws Exception{
		
		WebDriver driver = acessarAplicacao();
		
		try {
			
		//Clicar em addTodo
		driver.findElement(By.id("addTodo")).click();
		//Escrever descricao
		driver.findElement(By.id("task")).sendKeys("Teste Funcional");
		//Escrever a data
		Thread.sleep(3000);
		driver.findElement(By.id("dueDate")).sendKeys("01/01/2030");
		//Clicar em salvar
		Thread.sleep(3000);
		driver.findElement(By.id("saveButton")).click();
		//Validar mensagem de sucesso
		Thread.sleep(3000);
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!",message );
		
		}finally {
			driver.quit();
		}
	}
	
	@Test
    public void naoDeveSalvarTarefaSemDescricao() throws InterruptedException, MalformedURLException{
		
		WebDriver driver = acessarAplicacao();
		try {		
		//Clicar em addTodo
		driver.findElement(By.id("addTodo")).click();
		//Escrever a data
		driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("01/01/2030");
		//Clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		//Validar mensagem de sucesso
		Thread.sleep(3000);  
		String messsage =  driver.findElement(By.xpath("//p[@id='message']")).getText();
		Assert.assertEquals("Fill the task description",messsage);	
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws Exception{
		
		WebDriver driver = acessarAplicacao();
		try {
			
		//Clicar em addTodo
		driver.findElement(By.id("addTodo")).click();
		//Escrever descricao
		driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Funcional");
		//Clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		//Validar mensagem de sucesso
		Thread.sleep(3000);
		String messsage =  driver.findElement(By.xpath("//p[@id='message']")).getText();
		Assert.assertEquals("Fill the due date", messsage);
		}finally {
			driver.quit();
		}
	}	
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws Exception{
		
		WebDriver driver = acessarAplicacao();
		try {
			
		//Clicar em addTodo
		driver.findElement(By.id("addTodo")).click();				
		//Escrever descricao
		driver.findElement(By.xpath("//input[@id='task']")).sendKeys("Teste Funcional");
		//Escrever a data
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys("01/01/2010");
		//Clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		//Validar mensagem de sucesso
		Thread.sleep(3000);
		String messsage =  driver.findElement(By.xpath("//p[@id='message']")).getText();
		Assert.assertEquals("Due date must not be in past", messsage);
		
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void deveRemoverTarefaComSucesso() throws Exception{
		
		WebDriver driver = acessarAplicacao();
		
		try {
		//   INSERIR TAREFA	
		//Clicar em addTodo
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Teste Funcional");
		Thread.sleep(3000);
		driver.findElement(By.id("dueDate")).sendKeys("01/01/2030");
		Thread.sleep(3000);
		driver.findElement(By.id("saveButton")).click();
		Thread.sleep(3000);
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!",message );
		
		//Remover a tarefa
		driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
	     message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!",message );
		}finally {
			driver.quit();
		}
	}
}
