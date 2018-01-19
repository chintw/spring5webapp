package guru.springframework.spring5webapp.bootstrap;


import java.util.Optional;

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
		Book maryBook2 = new Book("Spideman2","66666",maryPublisher);
		Book maryBook3 = new Book("Spideman3","999999",maryPublisher);

		System.out.println("number of books read by Mary " + mary.getBooks().size());
		mary.getBooks().add(maryBook);

		maryBook.getAuthors().add(mary);
		this.publisherRepository.save(maryPublisher);
	
		this.bookRepository.save(maryBook);
		this.bookRepository.save(maryBook2);
		this.bookRepository.save(maryBook3);
		this.authorRepository.save(mary);

		mary.getBooks().add(maryBook2);
		mary.getBooks().add(maryBook3);

		this.authorRepository.save(mary);
		
		System.out.println("number of books read by Mary " + mary.getBooks().size());
		
		
//		Optional<Book> maryBook_get = this.bookRepository.findById(maryBook.getId());
//		System.out.println("Author size===>" + maryBook_get.get().getAuthors().isEmpty());
		
		
		Author david = new Author("David","Banner");
		Publisher davidPublisher = new Publisher("Harvard", "USA 123");
		Book davidBook = new Book("Hulk","4321",davidPublisher);
		
		david.getBooks().add(davidBook);
		//davidBook.getAuthors().add(david); <== not required as it has already be defined to use books for mapping into 1 relationship table
		this.publisherRepository.save(davidPublisher);
		this.bookRepository.save(davidBook);
		this.authorRepository.save(david);

		
		
		
	}

}
