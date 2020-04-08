package br.com.hgalli.tasks.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
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
    public void naoDeveSalvarTarefaSemDescricao() throws InterruptedException{
		
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
}
