# Spring AI Demo Project

This project demonstrates the integration of **Spring AI** with Spring Framework for building AI-powered applications.

## Why Spring AI and Spring AI Architecture?

**Spring AI** provides a unified API for interacting with various AI models and Large Language Models (LLMs) through a consistent, Spring-native abstraction layer. Key benefits include:

- **Unified API**: Standardized way to work with different AI providers (OpenAI, Claude, Ollama, etc.) without provider-specific code
- **Ease of Integration**: Seamless integration with Spring applications through autoconfiguration
- **Portability**: Switch between different LLM providers with minimal code changes
- **Spring Native**: Leverages Spring's ecosystem and best practices for dependency injection, configuration management, and lifecycle management
- **Reduced Complexity**: Abstracts away provider-specific complexities while offering powerful features like prompt templates, message history, and model switching
- **Flexible Deployment**: Supports both cloud-based providers and locally-hosted LLMs

## Technology Overview

Currently, Spring AI dependencies work seamlessly with **Spring 3**. While Spring 4 is supported, Spring 3 offers a richer ecosystem of available dependencies. To work with AI in Spring, follow these steps:

1. **Include Required Dependency**: Add the Spring AI starter dependency for your chosen provider (e.g., `spring-ai-openai-spring-boot-starter`)
2. **Leverage Spring Autoconfiguration**: Spring automatically configures beans based on classpath availability
3. **Use ChatClient Interface**: The `ChatClient` class is the primary interface for AI interactions and is automatically wired into your application

## How Spring AI Works

Spring AI abstracts the complexity of different AI models behind Spring's autoconfiguration features:

- **Provider Detection**: Once you add a dependency for a specific AI provider, Spring AI automatically detects and configures it at runtime
- **Auto-Configuration Files**: You can inspect the auto-configuration by navigating to:
  - External Libraries → `[dependency]` → `META-INF/spring` → Click on the configuration class
  - Scroll through the auto-configuration code to understand what's being set up
- **ChatClient Wrapping**: Spring developers wrap various AI models using autoconfiguration features with the `ChatClient` interface, providing a consistent API

## API Key and Model Configuration

⚠️ **Important**: Without proper API key configuration, the application cannot run in production.

### Option 1: Cloud-Based Providers (OpenAI, Claude, ChatGPT, etc.)

- **Requirement**: API key from the chosen provider
- **Setup**: Configure the API key in your `application.yaml` or environment variables
- **Cost**: API calls to cloud providers incur charges

### Option 2: Locally Hosted LLM (Recommended for Development)

- **No API Key Required**: Run LLMs locally without cloud costs
- **Flexibility**: Deploy via Docker or directly on your machine
- **Models**: By default, Spring AI uses the **Mistral** model
- **Note**: For advanced features (images, videos, byte streams), ensure your LLM supports them
- **Setup**: Configure the local LLM endpoint in your `application.yaml`

### Model Configuration

If using a different model than the default Mistral, you must configure it in your application properties for the runtime to work correctly.

## Useful Resources

- [Spring AI Documentation - Ollama Chat](https://docs.spring.io/spring-ai/reference/api/chat/ollama-chat.html)

---

## Git Commands Reference

Here are useful Git commands for managing your project:

### Check Git Origin URL
```bash
git remote -v
```
This will display the remote repository URL(s).

### List All Git Branches
```bash
git branch -a
```
- `git branch` - List local branches
- `git branch -a` - List all branches (local and remote)
- `git branch -r` - List only remote branches

### Create a New Git Branch
```bash
git branch <branch-name>
```

### Switch to a Branch
```bash
git checkout <branch-name>
```

### Create and Switch to a New Branch (in one command)
```bash
git checkout -b <branch-name>
```

### Push Branch to Remote
```bash
git push -u origin <branch-name>
```

### Delete a Local Branch
```bash
git branch -d <branch-name>
```

### Delete a Remote Branch
```bash
git push origin --delete <branch-name>
```
