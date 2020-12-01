import * as core from '@actions/core';
import * as github from '@actions/github';
import * as glob from '@actions/glob';

function getInputAsList(label: string, opts?: core.InputOptions): string[] {
  return core.getInput(label, opts).split("\n").map(s => s.trim()).filter(x => x !== '');
}

async function run() {
  try {
    const pr = github.context.payload.pull_request;
    const prTitle: string | null = pr?.title;
    if (prTitle && prTitle.includes('refine')) {
      core.info('Ignored a refine PR.');
      return;
    }

    core.setSecret('token');
    const token = core.getInput('token', {required: true});
    const files = getInputAsList('files', {required: true});
    core.info(`Checked files: ${files}`);

    for (let file of files) {
      let globber = await glob.create(file);
      let globResult = await globber.glob();
      let included = globResult.length > 0;
      core.info(`${file}: included: ${included}, globResult: ${globResult}`);
    }
  } catch (e) {
    core.setFailed(`Error: ${e}`);
  }
}

run();
