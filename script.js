document.getElementById('meuFormulario').addEventListener('submit', function(event) {
    // Impede o comportamento padrão do HTML de recarregar a página inteira
    event.preventDefault(); 
    
    const codigoInput = document.getElementById('codigo').value;
    const btn = document.getElementById('btnEnviar');
    const erroContainer = document.getElementById('erroContainer');

    // Esconde mensagens de erro anteriores
    erroContainer.style.display = 'none';

    // Simulação do envio assíncrono (Fetch API) para o servidor Java
    fetch('/api/cadastrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ codigo: codigoInput })
    })
    .then(async response => {
        if (response.ok) {
            // Se o Java responder 200 OK -> Aplica o Aprendizado 1 (Feedback de Sucesso)
            btn.textContent = "Enviado com Sucesso!";
            btn.classList.add('success');
            btn.disabled = true;
        } else {
            // Se o Java responder erro (ex: 400 Bad Request) -> Trata o erro sem recarregar a página
            const data = await response.json();
            throw new Error(data.erro || "Erro ao processar dados.");
        }
    })
    .catch(error => {
        // Exibe o erro de forma amigável na interface (Resolvendo o Aprendizado 2)
        erroContainer.textContent = error.message;
        erroContainer.style.display = 'block';
    });
});