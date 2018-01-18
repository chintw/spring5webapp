package guru.springframework.spring5webapp.bootstrap;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<org.springframework.context.event.ContextRefreshedEvent> 
{
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		initRepositories();
	}

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) 
	{
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }
	
	private void initRepositories() {
		// TODO Auto-generated method stub
		Author mary = new Author("Mary","Jane");
		Publisher maryPublisher = new Publisher("Cambridge", "Oxford 123");
		Book maryBook = new Book("Spideman","1234",maryPublisher);
		mary.getBooks().add(maryBook);
		maryBook.getAuthors().add(mary);
		this.publisherRepository.save(maryPublisher);
		this.bookRepository.save(maryBook);
		this.authorRepository.save(mary);

		Author david = new Author("David","Banner");
		Publisher davidPublisher = new Publisher("Harvard", "USA 123");
		Book davidBook = new Book("Hulk","4321",davidPublisher);
		
		david.getBooks().add(davidBook);
		davidBook.getAuthors().add(david);
		this.publisherRepository.save(davidPublisher);
		this.bookRepository.save(davidBook);
		this.authorRepository.save(david);

		
		
		
	}

}
